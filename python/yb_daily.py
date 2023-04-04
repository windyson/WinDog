# 从tushare获取全部股票的日线数更新到本地库stock_daily中，为后面的指标计算做准备
# 该程序将从2022-01-01开始获取日线数据，支持断点采集
# william
# 2022-05-26
# -------------------
# 新增了上证指数采集
# 两种上证指数方式
# 1、从tushare中获取，接口可能会被废弃，好处是可以获取指定时间段内数据，方便补齐历史数
# 2、从stock_pool中获取，缺点是无法补齐历史数据
# 2023-01-17

import MySQLdb
from datetime import datetime, date, timedelta
import pandas as pd
import tushare as ts
from soupsieve.util import lower
from sqlalchemy import create_engine
import YbConfig


def execMysqlCmd(sql):
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


# 先删除mysql老数据
def delete_data(daily):
    execMysqlCmd("DELETE FROM stock_daily WHERE trade_date = %s" % (daily))


# 再插入mysql新数据
def write_data(ds):
    res = ds.to_sql('stock_daily', engine_ts, index=False, if_exists='append', chunksize=5000)
    print(res)


# 获取新数据
def get_data(pr, daily):
    ds = pr.daily(trade_date=daily)
    # print(ds)
    ds.rename(columns={'close': 'cls', 'change': 'chg'}, inplace=True)
    return ds


# 更新股票池数据
def update_pool(pr, daily):
    print('更新股票池日期：%s' % daily)
    ds = pr.daily(trade_date=daily)
    # print(df)
    for index, row in ds.iterrows():
        strCode = row["ts_code"]
        strCode = lower(strCode[7:9]) + strCode[0:6]
        print("INSERT INTO stock_pool(code) VALUES ('%s') ON DUPLICATE KEY UPDATE code = values(code)" % strCode)
        execMysqlCmd("INSERT INTO stock_pool(code) VALUES ('%s') ON DUPLICATE KEY UPDATE code = values(code)" % strCode)


# 更新股票池数据
def update_daily(start, end):
    print('更新上证指数日线数据从%s 到 %s...' % (start, end))
    df = ts.get_hist_data('sh', start, end)  # 获取上证指数k线：'2023-01-15', '2023-01-17'
    # print(df)
    for index, row in df.iterrows():
        execMysqlCmd(
            "INSERT INTO stock_daily(ts_code, trade_date, open, high, low, cls, vol) VALUES ('%s', '%s', %s, %s, %s, %s, %s) ON DUPLICATE KEY UPDATE ts_code = values(ts_code), trade_date = values(trade_date)" % (
                '000001.SH', row.name.replace('-', '', 2), row.open, row.high, row.low, row.close, row.volume))
        print('更新上证指数日线数据[%s]成功！' % row.name)

    print('更新沪深300指数日线数据从%s 到 %s...' % (start, end))
    df = ts.get_hist_data('399300', start, end)  # 获取上证指数k线：'2023-01-15', '2023-01-17'
    # print(df)
    for index, row in df.iterrows():
        execMysqlCmd(
            "INSERT INTO stock_daily(ts_code, trade_date, open, high, low, cls, vol) VALUES ('%s', '%s', %s, %s, %s, %s, %s) ON DUPLICATE KEY UPDATE ts_code = values(ts_code), trade_date = values(trade_date)" % (
                '399300.SZ', row.name.replace('-', '', 2), row.open, row.high, row.low, row.close, row.volume))
        print('更新沪深300指数日线数据[%s]成功！' % row.name)


if __name__ == '__main__':

    # 读取配置文件
    conf = YbConfig.InitConfig().dic

    # 设置访问api的key
    pro = ts.pro_api(conf['token'])

    # # 打开数据库连接
    engine_ts = create_engine(conf['url'])
    tsdb = MySQLdb.connect(conf['ip'], conf['user'], conf['pwd'], "windog", charset='utf8')

    # 获取断点
    df = pd.read_sql(
        "select distinct trade_date from stock_daily where ts_code <> '000001.SH' group by trade_date order by trade_date asc ",
        engine_ts)
    if len(df) > 0:
        # 能找到历史数据，更新从断点开始
        startdt = df.iat[len(df) - 1, 0]
        # 取下一天
        startdt = datetime.strptime(startdt, '%Y%m%d') + timedelta(days=+1)
        startdt = startdt.strftime('%Y%m%d')
    else:
        startdt = "20230101"
    # 当前日期
    enddt = datetime.now().strftime('%Y%m%d')

    print("准备获取从 '%s' 到 '%s' 的交易日线数据..." % (startdt, enddt))
    df = pro.trade_cal(exchange='', start_date=startdt, end_date=enddt, fields='cal_date', is_open='1')
    if len(df) == 0:
        print("没有获取到从 '%s' 开始的交易日线数据！" % startdt)
    else:
        for index, row in df.iterrows():
            curdt = row["cal_date"]
            print("开始执行：" + curdt)
            delete_data(curdt)
            df = get_data(pro, curdt)
            write_data(df)
            print("保存完毕：" + curdt)

        # 更新股票池
        update_pool(pro, curdt)

    # 从pool中获取上证数据，更新daily
    update_daily(startdt[0:4] + '-' + startdt[4:6] + '-' + startdt[6:8],
                   enddt[0:4] + '-' + enddt[4:6] + '-' + enddt[6:8])
