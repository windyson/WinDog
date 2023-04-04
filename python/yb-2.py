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
import MySQLdb
import pandas as pd
from sqlalchemy import create_engine
from datetime import datetime, date, timedelta
import YbConfig

# 注意，在兼容模式为1时，表示使用metastock的技术指标计算方式， 与国内的主流股票软件计算方式一致
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


# 通过KDJ判断买进和卖出
def get_kdj(engine_ts, symbol, start_time, end_time, rtflag):
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

    # 参数9,3,3
    # MA_Type: 0=SMA, 1=EMA, 2=WMA, 3=DEMA, 4=TEMA, 5=TRIMA, 6=KAMA, 7=MAMA, 8=T3 (Default=SMA)
    slowk, slowd = talib.STOCH(df['high'].values, df['low'].values, df['cls'].values, fastk_period=9, slowk_period=5,
                               slowk_matype=1, slowd_period=5, slowd_matype=1)
    slowkMA5 = talib.MA(slowk, timeperiod=5)
    slowkMA10 = talib.MA(slowk, timeperiod=10)
    slowkMA20 = talib.MA(slowk, timeperiod=20)
    slowdMA5 = talib.MA(slowd, timeperiod=5)
    slowdMA10 = talib.MA(slowd, timeperiod=10)
    slowdMA20 = talib.MA(slowd, timeperiod=20)

    # 11,12,13 K,D,J
    df['slowk'] = pd.Series(slowk, index=df.index)  # K
    df['slowd'] = pd.Series(slowd, index=df.index)  # D
    df['slowj'] = pd.Series(3 * slowk - 2 * slowd, index=df.index)  # J
    dflen = df.shape[0]
    MAlen = len(slowdMA5)
    # print(df)
    operator = 0
    # 1.K线是快速确认线 -- 数值在90以上为超买信号，数值在10以下为超卖信号；2.D大于80为超卖状态，小于20为超卖状态
    if df.iat[(dflen - 1), 11] >= 90:
        operator = operator - 3
    if df.iat[(dflen - 1), 11] <= 10:
        operator = operator + 3
    if df.iat[(dflen - 1), 12] >= 80:
        operator = operator - 3
    if df.iat[(dflen - 1), 12] <= 20:
        operator = operator + 3
    # 上涨趋势中，K线向上穿过D线，黄金交叉，将进入多头市场，股价将上涨，应该买进
    if df.iat[(dflen - 1), 11] > df.iat[(dflen - 1), 12] and df.iat[(dflen - 2), 11] < df.iat[(dflen - 2), 12]:
        operator = operator + 10
    # 下降趋势中，K线向下穿过D线，死亡交叉，将进入空头市场，股价将下降，应该卖出
    if df.iat[(dflen - 1), 11] < df.iat[(dflen - 1), 12] and df.iat[(dflen - 2), 11] > df.iat[(dflen - 2), 12]:
        operator = operator - 10
    # 3.当随机指标与股价出现背离时，一般为转势的信号。
    if df.iat[(dflen - 1), 6] >= df.iat[(dflen - 1), 3] and df.iat[(dflen - 1), 3] >= df.iat[(dflen - 1), 7]:  # K线上涨
        if (slowkMA5[MAlen - 1] <= slowkMA10[MAlen - 1] and slowkMA10[MAlen - 1] <= slowkMA20[MAlen - 1]) or (
                slowdMA5[MAlen - 1] <= slowdMA10[MAlen - 1] and slowdMA10[MAlen - 1] <= slowdMA20[MAlen - 1]):  # K,D下降
            operator = operator - 1
    elif df.iat[(dflen - 1), 6] <= df.iat[(dflen - 1), 3] and df.iat[(dflen - 1), 3] <= df.iat[(dflen - 1), 7]:  # K线下降
        if (slowkMA5[MAlen - 1] >= slowkMA10[MAlen - 1] and slowkMA10[MAlen - 1] >= slowkMA20[MAlen - 1]) or (
                slowdMA5[MAlen - 1] >= slowdMA10[MAlen - 1] and slowdMA10[MAlen - 1] >= slowdMA20[MAlen - 1]):  # K,D上涨
            operator = operator + 1
    return operator


def get_all_stock_kdj(engine_ts, enddt, realtime):
    begdt = datetime.today() + timedelta(days=-180)
    begdt = begdt.strftime('%Y%m%d')
    df = pd.read_sql("select distinct ts_code from stock_daily order by ts_code asc", engine_ts)
    # 删除老数据
    execMysqlCmd(tsdb, "delete from stock_lib where insdt='%s' and metric='KDJ'" % (realtime))
    print("正在获取所有股票的KDJ，日期：'%s' ..." % (realtime))
    if enddt == realtime:
        flag = False
    else:
        flag = True;
    for index, row in df.iterrows():
        if '000001.SH' == row["ts_code"]:
            continue
        if '399300.SZ' == row["ts_code"]:
            continue
        ops = get_kdj(engine_ts, symbol=row["ts_code"], start_time=begdt, end_time=enddt, rtflag=flag)
        if ops > 10:
            print("股票名：'%s' --- 买：'%s'" % (row["ts_code"], ops))
            execMysqlCmd(tsdb, "insert into stock_lib(code, insdt, metric, val) values('%s', '%s', '%s', '%s')" % (
            row["ts_code"], realtime, "KDJ", ops))
            execMysqlCmd(tsdb, "update stock_pool set vol2=1 where code='%s'" % (
                        row["ts_code"][7:9].lower() + row["ts_code"][0:6]))
        # if ops < -10:
        #     print("股票名：'%s' --- 卖：'%s'" %(row["ts_code"], ops))
        #     execMysqlCmd(tsdb, "insert into stock_lib(code, insdt, metric, val) values('%s', '%s', '%s', '%s')" %(row["ts_code"], realtime, "KDJ", ops))
    # 删除停牌的和st的
    execMysqlCmd(tsdb,
                 "delete from stock_lib where insdt ='%s' and SUBSTR(code , 1, 6) in (select SUBSTR(code , 3, 6) from stock_pool where vbeg = 0 or grade < 0)" % (
                     realtime))
    print("获取所有股票的KDJ完毕['%s']，结果保存在stock_lib表中！" % (realtime))


def get_one_stock_kdj(engine_ts, begdt, code):
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
            ops = get_kdj(engine_ts, symbol=code, start_time=begdt, end_time=enddt, rtflag=False)
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
    get_all_stock_kdj(engine_ts, enddt, df.iat[df.shape[0] - 1, 0].strftime('%Y%m%d'))  # 没有改日收盘数据，可以加上改日收盘数据计算

    # 获取指定股票一段时间的买点信息
    # dfstock = pd.read_sql("select distinct ts_code from stock_daily group by ts_code order by ts_code asc", engine_ts)
    # for index, row in dfstock.iterrows():
    #     get_one_stock_kdj(engine_ts, '20210101', row["ts_code"])

print("程序执行完毕！")
