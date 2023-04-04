# 从tushare获取全部股票的日线数更新到本地库stock_daily中，为后面的指标计算做准备
# 该程序将从2022-01-01开始获取日线数据，支持断点采集
# william
# 2022-05-26
import os

import MySQLdb
from datetime import datetime, date, timedelta
import pandas as pd
import tushare as ts
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
    execMysqlCmd("DELETE FROM stock_cal WHERE opendt >= %s" % (daily))


# 再插入mysql新数据
def insert_data(daily):
    execMysqlCmd("insert into stock_cal(opendt) values('%s')" % (daily))


if __name__ == '__main__':

    # 读取配置文件
    conf = YbConfig.InitConfig().dic

    # 设置访问api的key
    pro = ts.pro_api(conf['token'])

    # # 打开数据库连接
    engine_ts = create_engine(conf['url'])
    tsdb = MySQLdb.connect(conf['ip'], conf['user'], conf['pwd'], "windog", charset='utf8')

    # 获取断点
    df = pd.read_sql("select max(opendt) trade_date from stock_cal", engine_ts)
    if len(df) > 0:
        # 能找到历史数据，更新从断点开始
        startdt = df.iat[0, 0]
        if (None == startdt):
            startdt = '20230101'
        # 取下一天
        startdt = datetime.strptime(startdt, '%Y%m%d') + timedelta(days=+1)
        startdt = startdt.strftime('%Y%m%d')
    else:
        startdt = "20220101"

    enddt = datetime.today() + timedelta(days=+30)
    enddt = enddt.strftime('%Y%m%d')
    print("准备获取从 '%s' 到 '%s' 的股市日历..." % (startdt, enddt))

    df = pro.trade_cal(exchange='', start_date=startdt, end_date=enddt, fields='cal_date', is_open='1')
    if len(df) == 0:
        print("没有获取到从 '%s' 开始的交易日信息！" % (startdt))
    else:
        for index, row in df.iterrows():
            curdt = row["cal_date"]
            print("找到开盘日期：" + curdt)
            insert_data(curdt)
            print("保存完毕：" + curdt)
