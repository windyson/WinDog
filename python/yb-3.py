# 基于表stock_daily中的数据计算策略，不依赖外部数据源
# 计算结果体现为买入卖出信号量，存入stock_lib表中
# 此方法为基本指标模型，其他指标体系参照此方法编写

# 步骤1：查询stock_daily中的数据数据区间
# 步骤2：逐一计算最后一日的指标
# 步骤3：将满足条件的选股写入stock_lib表中
# 支持分析指定股票在一段时间内的买卖点
# william
# 2022-05-26
import os

import talib
import MySQLdb
import numpy as np
import pandas as pd
from sqlalchemy import create_engine
from datetime import datetime, date, timedelta

# 注意，在兼容模式为1时，表示使用metastock的技术指标计算方式， 与国内的主流股票软件计算方式一致
import YbConfig

talib.set_compatibility(1)


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
    tsdb.close()


# 通过RSI判断买入卖出
def get_rsi(engine_ts, symbol, start_time, end_time, rtflag):
    # 积分器
    operator = 0
    if rtflag:
        # 加入当前行情一起计算
        df = pd.read_sql(
            "select * from stock_daily where ts_code = '%s' and trade_date >= '%s' and trade_date <= '%s' UNION select code, '99999999' trade_date , vbeg, vhigh, vlow, vend, lastend, 0 , 0 , vtotal, vamount from stock_pool where code='%s' order by trade_date asc" % (
            symbol, start_time, end_time, symbol[7:9].lower() + symbol[0:6].lower()), engine_ts)  # 读入数据库中已有的行情表
    else:
        # 完全用日线数据计算
        df = pd.read_sql(
            "select * from stock_daily where ts_code = '%s' and trade_date >= '%s' and trade_date <= '%s' order by trade_date asc" % (
            symbol, start_time, end_time), engine_ts)  # 读入数据库中已有的行情表
    # 加入一条重复的
    # df = pd.read_sql("select * from stock_daily where ts_code = '%s' and trade_date >= '%s' and trade_date <= '%s' UNION select ts_code, '99999999' trade_date , open, high, low, close, pre_close, `change`, pct_chg , vol, amount from stock_daily where ts_code = '%s' and trade_date = '%s' order by trade_date asc" %(symbol, start_time, end_time, symbol, end_time), engine_ts)# 读入数据库中已有的行情表
    # 加入一条平值记录
    # df = pd.read_sql("select * from stock_daily where ts_code = '%s' and trade_date >= '%s' and trade_date <= '%s' UNION select ts_code, '99999999' trade_date , close, close, close, close, pre_close, `change`, pct_chg , vol, amount from stock_daily where ts_code = '%s' and trade_date = '%s' order by trade_date asc" %(symbol, start_time, end_time, symbol, end_time), engine_ts)# 读入数据库中已有的行情表
    # print(df)
    if df.shape[0] < 35:
        # print("'%s' 没有获得日线数据开始日：'%s'   结束日：'%s'" %(symbol, start_time, end_time))
        return operator

    # 参数14,5             股票软件用的参数为：12，6
    slowreal = talib.RSI(np.array(df['cls']), timeperiod=12)
    fastreal = talib.RSI(np.array(df['cls']), timeperiod=6)
    slowrealMA5 = talib.MA(slowreal, timeperiod=5, matype=0)
    slowrealMA10 = talib.MA(slowreal, timeperiod=10, matype=0)
    slowrealMA20 = talib.MA(slowreal, timeperiod=20, matype=0)
    fastrealMA5 = talib.MA(fastreal, timeperiod=5, matype=0)
    fastrealMA10 = talib.MA(fastreal, timeperiod=10, matype=0)
    fastrealMA20 = talib.MA(fastreal, timeperiod=20, matype=0)
    # 18-19 慢速real，快速real
    df['slowreal'] = pd.Series(slowreal, index=df.index)  # 慢速real 18
    df['fastreal'] = pd.Series(fastreal, index=df.index)  # 快速real 19
    dflen = df.shape[0]
    MAlen = len(slowrealMA5)
    operate = 0

    # print(df)
    # RSI>80为超买区，RSI<20为超卖区
    # print("'%s'  '%s'  '%s' '%s'  '%s'" %(symbol, df.iat[(dflen-1),11], df.iat[(dflen-1),12], df.iat[(dflen-2),11], df.iat[(dflen-2),12]))
    if df.iat[(dflen - 1), 11] > 80 or df.iat[(dflen - 1), 12] > 80:
        operate = operate - 2
    elif df.iat[(dflen - 1), 11] < 20 or df.iat[(dflen - 1), 12] < 20:
        operate = operate + 2

    # RSI上穿50分界线为买入信号，下破50分界线为卖出信号
    if (df.iat[(dflen - 2), 11] <= 50 and df.iat[(dflen - 1), 11] > 50) or (
            df.iat[(dflen - 2), 12] <= 50 and df.iat[(dflen - 1), 12] > 12):
        operate = operate + 4
    elif (df.iat[(dflen - 2), 11] >= 50 and df.iat[(dflen - 1), 11] < 50) or (
            df.iat[(dflen - 2), 12] >= 50 and df.iat[(dflen - 1), 12] < 50):
        operate = operate - 4

    # #RSI掉头向下为卖出讯号，RSI掉头向上为买入信号
    if df.iat[(dflen - 1), 6] >= df.iat[(dflen - 1), 3] and df.iat[(dflen - 1), 3] >= df.iat[(dflen - 1), 7]:  # K线上涨
        if (slowrealMA5[MAlen - 1] <= slowrealMA10[MAlen - 1] and slowrealMA10[MAlen - 1] <= slowrealMA20[
            MAlen - 1]) or (
                fastrealMA5[MAlen - 1] <= fastrealMA10[MAlen - 1] and fastrealMA10[MAlen - 1] <= fastrealMA20[
            MAlen - 1]):  # RSI下降
            operate = operate - 1
    elif df.iat[(dflen - 1), 6] <= df.iat[(dflen - 1), 3] and df.iat[(dflen - 1), 3] <= df.iat[(dflen - 1), 7]:  # K线下降
        if (slowrealMA5[MAlen - 1] >= slowrealMA10[MAlen - 1] and slowrealMA10[MAlen - 1] >= slowrealMA20[
            MAlen - 1]) or (
                fastrealMA5[MAlen - 1] >= fastrealMA10[MAlen - 1] and fastrealMA10[MAlen - 1] >= fastrealMA20[
            MAlen - 1]):  # RSI上涨
            operate = operate + 1

    # 慢速线与快速线比较观察，若两线同向上，升势较强；若两线同向下，跌势较强；若快速线上穿慢速线为买入信号；若快速线下穿慢速线为卖出信号
    if df.iat[(dflen - 1), 12] > df.iat[(dflen - 1), 11] and df.iat[(dflen - 2), 12] <= df.iat[(dflen - 2), 11]:
        operate = operate + 10
    elif df.iat[(dflen - 1), 12] < df.iat[(dflen - 1), 11] and df.iat[(dflen - 2), 12] >= df.iat[(dflen - 2), 11]:
        operate = operate - 10

    return operate


def get_all_stock_rsi(engine_ts, enddt, realtime):
    begdt = datetime.today() + timedelta(days=-180)
    begdt = begdt.strftime('%Y%m%d')
    df = pd.read_sql("select distinct ts_code from stock_daily order by ts_code asc", engine_ts)
    # 删除老数据
    execMysqlCmd(tsdb, "delete from stock_lib where insdt='%s' and metric='RSI'" % (realtime))
    print("正在获取所有股票的RSI，日期：'%s' ..." % (realtime))
    if enddt == realtime:
        flag = False
    else:
        flag = True;
    for index, row in df.iterrows():
        if '000001.SH' == row["ts_code"]:
            continue
        if '399300.SZ' == row["ts_code"]:
            continue
        ops = get_rsi(engine_ts, symbol=row["ts_code"], start_time=begdt, end_time=enddt, rtflag=flag)
        if ops > 10:
            print("股票名：'%s' --- 买：'%s'" % (row["ts_code"], ops))
            execMysqlCmd(tsdb, "insert into stock_lib(code, insdt, metric, val) values('%s', '%s', '%s', '%s')" % (
            row["ts_code"], realtime, "RSI", ops))
            execMysqlCmd(tsdb, "update stock_pool set vol3=1 where code='%s'" % (
                        row["ts_code"][7:9].lower() + row["ts_code"][0:6]))
        # if ops < -10:
        #     print("股票名：'%s' --- 卖：'%s'" %(row["ts_code"], ops))
        #     execMysqlCmd(tsdb, "insert into stock_lib(code, insdt, metric, val) values('%s', '%s', '%s', '%s')" %(row["ts_code"], realtime, "RSI", ops))
    # 删除停牌的和st的
    execMysqlCmd(tsdb,
                 "delete from stock_lib where insdt ='%s' and SUBSTR(code , 1, 6) in (select SUBSTR(code , 3, 6) from stock_pool where vbeg = 0 or grade < 0)" % (
                     realtime))
    print("获取所有股票的RSI完毕['%s']，结果保存在stock_lib表中！" % (realtime))


def get_one_stock_rsi(engine_ts, begdt, code):
    # 计算指定股票一段时间的买卖点
    df = pd.read_sql(
        "select distinct trade_date from stock_daily where trade_date >='%s' group by trade_date order by trade_date asc" % (
            begdt), engine_ts)

    if len(df) == 0:
        print("没有获取到股票列表信息，请检查stock_daily表是否有数据")
    else:
        print("正在计算股票 '%s' 从 '%s' 到 '%s' 的买卖点 ..." % (code, begdt, datetime.today().strftime('%Y%m%d')))
        for index, row in df.iterrows():
            begdt = datetime.strptime(row["trade_date"], '%Y%m%d') + timedelta(days=-180)
            begdt = begdt.strftime('%Y%m%d')
            enddt = row["trade_date"]
            # print("   获取数据从 '%s' 到 '%s' 的买卖点" %(begdt, enddt))
            ops = get_rsi(engine_ts, symbol=code, start_time=begdt, end_time=enddt, rtflag=False)
            if ops >= 10:
                print("日期：'%s' --- 买：'%s'" % (row["trade_date"], ops))
            # if ops < -10:
            #     print("日期：'%s' --- 卖：'%s'" %(row["trade_date"], ops))


if __name__ == '__main__':
    # 读取配置文件
    conf = YbConfig.InitConfig().dic

    # # 打开数据库连接
    engine_ts = create_engine(conf['url'])
    tsdb = MySQLdb.connect(conf['ip'], conf['user'], conf['pwd'], "windog", charset='utf8')

    df = pd.read_sql("select max(trade_date) from stock_daily", engine_ts)
    if len(df) == 0:
        print("没有获取到股票列表信息，请检查stock_daily表是否有数据")
        sys.exit()
    else:
        enddt = df.iat[df.shape[0] - 1, 0]
        # enddt ='20220526'

    # 先判断是否需要增加当前行情数据一起分析
    df = pd.read_sql("select max(update_dt) from stock_pool", engine_ts)
    # 如果行情数据和最新日线数据一致，就没必要增加日线数据一起计算了
    get_all_stock_rsi(engine_ts, enddt, df.iat[df.shape[0] - 1, 0].strftime('%Y%m%d'))  # 没有改日收盘数据，可以加上改日收盘数据计算

    # 获取指定股票一段时间的买点信息
    # dfstock = pd.read_sql("select distinct ts_code from stock_daily group by ts_code order by ts_code asc", engine_ts)
    # for index, row in dfstock.iterrows():
    #     get_one_stock_rsi(engine_ts, '20210101', row["ts_code"])

print("程序执行完毕！")
