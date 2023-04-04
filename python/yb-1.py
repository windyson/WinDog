# 基于表stock_daily中的数据计算策略，不依赖外部数据源
# 计算结果体现为买入卖出信号量，存入stock_lib表中
# 此方法为基本指标模型，其他指标体系参照此方法编写

# 步骤1：查询stock_daily中的数据数据区间
# 步骤2：逐一计算最后一日的指标
# 步骤3：将满足条件的选股写入stock_lib表中
# 支持分析指定股票在一段时间内的买卖点
# william
# 2022-05-26

import talib
import numpy as np
import MySQLdb
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


# 满足曲线平滑程度
def get_fit_smooth(df, icount, ifit):
    dflen = df.shape[0] - 1
    iret = 0
    for iloop in range(icount):
        # 从指定行开始，满足条件+1
        if df.iat[(dflen - iloop), 11] + df.iat[(dflen - iloop - 2), 11] > 2 * df.iat[dflen - iloop - 1, 11]:
            iret = iret + 1

    # 返回结果
    # print(iret)
    if iret >= ifit:
        return True
    else:
        return False


# 满足曲线弯曲距离
def get_fit_diff(df, icount, ifit):
    dflen = df.shape[0] - 1
    difmax = np.nanmax(df['signal'] - df['macd'])
    for iloop in range(icount):
        # 从指定行开始，满足条件+1
        if df.iat[(dflen - iloop), 12] - df.iat[(dflen - iloop), 11] > ifit * difmax:
            return True
            break
    return False


# 满足穿越条件
def get_fit_cross(df, icount):
    dflen = df.shape[0] - 1
    if df.iat[(dflen), 11] > df.iat[(dflen), 12]:
        for iloop in range(icount):
            if df.iat[(dflen - iloop - 1), 11] > df.iat[(dflen - iloop - 1), 12]:
                return False
                break
        return True
    return False


# 满足曲线弯曲深度
def get_fit_deep(df, icount, ifit):
    dflen = df.shape[0] - 1
    deep = df.iat[(dflen), 11]
    for iloop in range(icount):
        # 从指定行开始，满足条件+1
        if df.iat[(dflen - iloop), 11] < deep:
            deep = df.iat[(dflen - iloop), 11]
            continue
        else:
            if df.iat[(dflen - iloop), 11] - df.iat[(dflen), 11] > ifit * (df.iat[(dflen), 11] - deep):
                return True
    return False


# 根据macd指标获取某个股票是否需要买入或者卖出
def get_macd(engine_ts, symbol, start_time, end_time, rtflag):
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
    macd, macdsignal, macdhist = talib.MACD(df['cls'].values, fastperiod=12, slowperiod=26, signalperiod=9)
    my_ma5 = talib.MA(df['cls'].values, 5)
    my_ma10 = talib.MA(df['cls'].values, 10)
    my_ma20 = talib.MA(df['cls'].values, 20)
    # 13-15 DIF DEA DIF-DEA
    df['macd'] = pd.Series(macd, index=df.index)  # DIF
    df['signal'] = pd.Series(macdsignal, index=df.index)  # DEA
    df['macdhist'] = pd.Series(macdhist, index=df.index)  # DIF-DEA
    # print(df)
    # 获取行数
    dflen = df.shape[0]
    MAlen = len(my_ma5)

    # print("最小元素为:", min(list1))
    # 俩个数组 1.DIF、DEA均为正，DIF向上穿过DEA
    #        2.DIF、DEA均为负，DIF向下穿过DEA  and get_fit_smooth(df, 7, 4) and get_fit_diff(df, 10, 0.5)
    if get_fit_cross(df, 6) and get_fit_deep(df, 12, 2):
        # if get_fit_cross(df, 8) and get_fit_smooth(df, 10, 8) and get_fit_diff(df, 10, 0.8):
        operator = operator + 10  # 买进

    # if df.iat[(dflen-1),11] < df.iat[(dflen-1),12] and df.iat[(dflen-2),11] > df.iat[(dflen-2),12]:
    #     operator = operator -10#卖出

    # #DEA与K线发生背离 K线趋势向上，MACD向下，顶背离，将要下降；K线趋势向下，MACD向上，底背离，将要上升
    # # if df.iat[(dflen-1),6]>=df.iat[(dflen-1),3] and df.iat[(dflen-1),6]>=df.iat[(dflen-1),7]:#K线上涨
    # if df.iat[(dflen-1),6]>=df.iat[(dflen-1),3] and df.iat[(dflen-1),3]>=df.iat[(dflen-1),7]:#K线上涨
    #     if my_ma5[MAlen-1]<=my_ma10[MAlen-1] and my_ma10[MAlen-1]<=my_ma20[MAlen-1]:#DEA下降
    #         operator = operator-1
    #         # print("MA5:'%s'  MA10:'%s'  MA20:'%s'  " %(my_ma5[MAlen-1], my_ma10[MAlen-1], my_ma20[MAlen-1]))

    # if df.iat[(dflen-1),6]<=df.iat[(dflen-1),3] and df.iat[(dflen-1),6]<=df.iat[(dflen-1),7]:#K线下降
    # elif df.iat[(dflen-1),6]<=df.iat[(dflen-1),3] and df.iat[(dflen-1),3]<=df.iat[(dflen-1),7]:#K线下降
    #     if my_ma5[MAlen-1]>=my_ma10[MAlen-1] and my_ma10[MAlen-1]>=my_ma20[MAlen-1]:#DEA上升
    #         operator = operator+1
    #         # print("MA5:'%s'  MA10:'%s'  MA20:'%s'  " %(my_ma5[MAlen-1], my_ma10[MAlen-1], my_ma20[MAlen-1]))

    # #分析MACD柱状图 由负变正将上涨
    # if df.iat[(dflen-1),13] >0 and dflen >30:
    #     for i in range(1,3):   #26
    #         if df.iat[(dflen-1-i),13] <=0:
    #             operator = operator +5
    #             # print(df)
    #             break
    # # 由正变负 将降低
    # if df.iat[(dflen-1),13] <0 and dflen >30:
    #     for i in range(1,3):   #26
    #         if df.iat[(dflen-1-i),13] >=0:
    #             operator = operator -5
    #             # print(df)
    #             break

    return operator


def get_all_stock_macd(engine_ts, enddt, realtime):
    begdt = datetime.today() + timedelta(days=-180)
    begdt = begdt.strftime('%Y%m%d')
    df = pd.read_sql("select distinct ts_code from stock_daily order by ts_code asc", engine_ts)
    # 删除老数据
    execMysqlCmd(tsdb, "delete from stock_lib where insdt='%s' and metric='MACD'" % (realtime))
    print("正在获取所有股票的MACD，日期：'%s' ..." % (realtime))
    if enddt == realtime:
        flag = False
    else:
        flag = True;
    for index, row in df.iterrows():
        if '000001.SH' == row["ts_code"]:
            continue
        if '399300.SZ' == row["ts_code"]:
            continue
        ops = get_macd(engine_ts, symbol=row["ts_code"], start_time=begdt, end_time=enddt, rtflag=flag)
        if ops > 5:
            print("股票名：'%s' --- 买：'%s'" % (row["ts_code"], ops))
            execMysqlCmd(tsdb, "insert into stock_lib(code, insdt, metric, val) values('%s', '%s', '%s', '%s')" % (
                row["ts_code"], realtime, "MACD", ops))
            execMysqlCmd(tsdb, "update stock_pool set vol1=1 where code='%s'" % (
                    row["ts_code"][7:9].lower() + row["ts_code"][0:6]))
        if ops < -5:
            print("股票名：'%s' --- 卖：'%s'" % (row["ts_code"], ops))
            execMysqlCmd(tsdb, "insert into stock_lib(code, insdt, metric, val) values('%s', '%s', '%s', '%s')" % (
                row["ts_code"], realtime, "MACD", ops))
    # 删除停牌的和st的
    execMysqlCmd(tsdb,
                 "delete from stock_lib where insdt ='%s' and SUBSTR(code , 1, 6) in (select SUBSTR(code , 3, 6) from stock_pool where vbeg = 0 or grade < 0)" % (
                     realtime))
    print("获取所有股票的MACD完毕['%s']，结果保存在stock_lib表中！" % (realtime))


def get_one_stock_macd(engine_ts, begdt, code):
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
            ops = get_macd(engine_ts, symbol=code, start_time=begdt, end_time=enddt, rtflag=False)
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
    # 获取日线数据是否存在
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
    get_all_stock_macd(engine_ts, enddt, df.iat[df.shape[0] - 1, 0].strftime('%Y%m%d'))  # 没有改日收盘数据，可以加上改日收盘数据计算

    # 获取指定股票一段时间的买点信息
    # dfstock = pd.read_sql("select distinct ts_code from stock_daily group by ts_code order by ts_code asc", engine_ts)
    # for index, row in dfstock.iterrows():
    #     get_one_stock_macd(engine_ts, '20210101', row["ts_code"])

print("程序执行完毕！")
