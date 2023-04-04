import json
import MySQLdb
import redis as redis
import requests
import pandas as pd
from soupsieve.util import lower
from sqlalchemy import create_engine
from datetime import datetime

# 更新数据库
import YbConfig


def execMysqlCmd(tsdb, sql):
    # 使用cursor()方法获取操作游标
    cursor = tsdb.cursor()
    try:
        # 执行SQL语句
        cursor.execute(sql)
        # 提交修改
        tsdb.commit()
        return True
    except:
        # 发生错误时回滚
        tsdb.rollback()
        return False
    # 关闭连接
    # tsdb.close()


# 网易接口
def updateQuotesNetease(df):
    codeStr = ''
    for index, row in df.iterrows():
        # 分别拼装上交所，深交所，北交所的链接
        if 'sh' == row["code"][0:2]:
            codeStr = codeStr + '0' + row["code"][2:8] + ','
        elif 'sz' == row["code"][0:2]:
            codeStr = codeStr + '1' + row["code"][2:8] + ','
        else:
            codeStr = codeStr + '2' + row["code"][2:8] + ','

        # 一次获取25个股票的信息
        if (len(codeStr) < 400) and (index + 1 < df.shape[0]):
            continue

        url = 'http://api.money.126.net/data/feed/' + codeStr + '%2Cmoney.api'
        # 获取实时数据
        try:
            p = requests.post(url)
            resStr = p.text
        except:
            print('连接行情系统失败[' + row["code"] + ']:' + url)
            # traceback.print_exc()
            continue

        # 获取成功
        if (resStr.find("_ntes_quote_callback") == -1) or (len(resStr) < 100):
            print("..获取：%s[%s]行情信息失败！！！%s" % (row["cname"], row["code"], index + 1))
        else:
            resStr = resStr[21:len(resStr) - 2]

            # 更新数据库
            jobj = json.loads(resStr)

            # 根据字符串分割，循环更新所获取的行情信息
            codeLst = codeStr.split(',')
            for i in range(len(codeLst) - 1):
                curCode = codeLst[i]
                if not (curCode in resStr):
                    print("..获取：[%s]行情信息失败！！！%s" % (codeLst[i], index + 1))
                    continue

                # 更新等级
                grade = 1
                if ('*' in jobj[curCode]["name"]) or ('ST' in jobj[curCode]["name"]) or ('退' in jobj[curCode]["name"]):
                    # 评为垃圾级
                    grade = -3

                # 修正更新时间
                curdt = jobj[curCode]["update"][0:10]
                if (((curdt + ' 11:30:00') >= jobj[curCode]["update"]) and (
                        (curdt + ' 09:30:00') <= jobj[curCode]["update"])) or (
                        ((curdt + ' 15:00:00') >= jobj[curCode]["update"]) and (
                        (curdt + ' 13:00:00') <= jobj[curCode]["update"])):
                    curdt = jobj[curCode]["update"]
                elif (curdt + ' 15:00:00') < jobj[curCode]["update"]:
                    curdt = curdt + ' 15:00:00'
                elif (curdt + ' 11:30:00') < jobj[curCode]["update"]:
                    curdt = curdt + ' 11:30:00'
                else:
                    curdt = curdt + ' 09:30:00'

                sql = "update stock_pool set cname='%s',vbeg=%s,vend=%s,lastend=%s,vhigh=%s,vlow=%s,vtotal=%s,vamount=%s"
                sql = sql + ",grade=%s,cur_price=%s,update_dt='%s' where code='%s'"
                sql = sql % (
                    jobj[curCode]["name"], jobj[curCode]["open"], jobj[curCode]["price"], jobj[curCode]["yestclose"],
                    jobj[curCode]["high"], jobj[curCode]["low"],
                    jobj[curCode]["volume"], jobj[curCode]["turnover"], grade, jobj[curCode]["price"], curdt,
                    lower(jobj[curCode]["type"]) + curCode[1:7])
                # print(sql)
                if not execMysqlCmd(tsdb, sql):
                    print("..更新：%s[%s]行情信息失败！！！%s" % (
                        jobj[curCode]["name"], lower(jobj[curCode]["type"]) + curCode[1:7], index + 1))

        # 重置变量，获取下一次批量股票代码
        codeStr = ''


# 腾讯接口
# https://qt.gtimg.cn/q=sh600021,bj873152,sz300030
def updateAllQuotesTencent(df, redis_conn):
    dicStock = {}
    codeStr = ''
    print('开始执行获取腾讯行情...%s' % datetime.now().strftime('%Y-%m-%d %H:%M:%S'))
    for index, row in df.iterrows():
        codeStr = codeStr + row["code"] + ','

        # 一次获取201个股票的信息
        if (len(codeStr) < 2700) and (index + 1 < df.shape[0]):
            continue

        url = "https://qt.gtimg.cn/q=" + codeStr
        # 获取实时数据
        try:
            p = requests.get(url)
            resStr = p.text
            # print(resStr)
        except:
            print('连接行情系统失败[' + row["code"] + ']:' + url)
            # traceback.print_exc()
            continue

        # 获取成功
        if (len(resStr) < 100):
            print("..获取：%s[%s]行情信息失败！！！%s" % (row["cname"], row["code"], index + 1))
        else:
            resStr = resStr.replace("~~", "~")

        # 分割不同的股票
        stockLst = resStr.split(';')
        # 打开游标
        cursor = tsdb.cursor()
        try:
            for itemStr in iter(stockLst):
                if 5 > len(itemStr):
                    continue
                # 根据字符串分割，循环更新所获取的行情信息
                valueLst = itemStr.lstrip().split('~')
                if 30 > len(valueLst):
                    print('数据格式错误：%s' % itemStr)
                    continue
                # 更新等级
                grade = 1
                if ('*' in valueLst[1]) or ('ST' in valueLst[1]) or ('退' in valueLst[1]):
                    # 评为垃圾级
                    grade = -3

                # 修正更新时间
                curdt = valueLst[29]
                if (('113000' >= curdt[8:14]) and (
                        '093000' <= curdt[8:14])) or (
                        ('150000' >= curdt[8:14]) and (
                        ('130000' <= curdt[8:14]))):
                    curdt = curdt[0:4] + '-' + curdt[4:6] + '-' + curdt[6:8] + ' ' + curdt[8:10] + ':' + curdt[10:12] + ':' + curdt[12:14]
                elif ('150000') < curdt[8:14]:
                    curdt = curdt[0:4] + '-' + curdt[4:6] + '-' + curdt[6:8] + ' 15:00:00'
                elif ('113000') < curdt[8:14]:
                    curdt = curdt[0:4] + '-' + curdt[4:6] + '-' + curdt[6:8] + ' 11:30:00'
                else:
                    curdt = curdt[0:4] + '-' + curdt[4:6] + '-' + curdt[6:8] + ' 09:30:00'

                # 更新字典数据，保存分钟级数据
                dicDetail = {}
                # dicDetail.update({'code': valueLst[0][2:10]})
                # dicDetail.update({'curtm': curdt[11:13] + curdt[14:16]})
                dicDetail.update({'open': float(valueLst[5])})
                dicDetail.update({'current': float(valueLst[3])})
                dicDetail.update({'lastclose': float(valueLst[4])})
                dicDetail.update({'high': float(valueLst[32])})
                dicDetail.update({'low': float(valueLst[33])})
                dicDetail.update({'total': float(valueLst[6])})
                dicDetail.update({'amount': float(valueLst[7])})
                dicStock.update({valueLst[0][2:10]: dicDetail})
                redis_conn.set(curdt[11:13] + curdt[14:16] + valueLst[0][2:10], dicDetail.__str__())
                # 当前行情，内部保存时间
                dicDetail.update({'curtm': curdt[11:13] + curdt[14:16]})
                redis_conn.set(valueLst[0][2:10], dicDetail.__str__())
                # 修改股票当前价格
                sql = "update stock_pool set cname='%s',vbeg=%s,vend=%s,lastend=%s,vhigh=%s,vlow=%s,vtotal=%s,vamount=%s"
                sql = sql + ",grade=%s,cur_price=%s,update_dt='%s' where code='%s'"
                sql = sql % (
                    valueLst[1], valueLst[5], valueLst[3], valueLst[4],
                    valueLst[32], valueLst[33],
                    valueLst[6], valueLst[7], grade, valueLst[3], curdt,
                    valueLst[0][2:10])
                # print(sql)
                cursor.execute(sql)
                # 重置变量，获取下一次批量股票代码
                codeStr = ''
            # 提交修改
            tsdb.commit()
        except:
            print("..更新：%s[%s]行情信息失败！！！%s" % (valueLst[1], valueLst[0][3:11], index + 1))
            # 发生错误时回滚
            tsdb.rollback()
    print('执行获取腾讯行情完毕!!!%s' % datetime.now().strftime('%Y-%m-%d %H:%M:%S'))

    # 全部股票行情获取完毕后，将分钟级数据发送到其他组件，完成分拣保存
    # 这样处理的好处是拆分系统功能，保持灵活性
    # 一次行情获取，可能分钟级数据是混杂的，需要按时分归类存放
    # if '00' == curdt[17:19] or '30' < curdt[17:19]:
    #     print('测试信息：应该保留行情数据--------------------' + curdt[17:19])
    #     try:
    #         # 保存当下行情数据
    #         res = requests.post(url='http://127.0.0.1:5000/setStockQuotes',
    #                             headers={"Content-Type": "application/json"},
    #                             json={"curdt": curdt[0:4] + curdt[5:7] + curdt[8:10], "data": str(dicStock)})
    #         print('执行保存腾讯行情成功!!!%s  返回值:%s' % (datetime.now().strftime('%Y-%m-%d %H:%M:%S'), res.text))
    #     except:
    #         print('执行保存腾讯行情失败!!!')


if __name__ == '__main__':
    # 读取配置文件
    conf = YbConfig.InitConfig().dic

    # 打开数据库连接
    engine_ts = create_engine(conf['url'])
    tsdb = MySQLdb.connect(conf['ip'], conf['user'], conf['pwd'], "windog", charset='utf8')

    df = pd.read_sql("select * from stock_pool order by update_dt asc", engine_ts)
    # updateQuotesNetease(df)

    redis_conn = redis.Redis(host='127.0.0.1', port=6379, db=0)

    updateAllQuotesTencent(df, redis_conn)
