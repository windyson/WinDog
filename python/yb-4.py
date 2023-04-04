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
import pandas as pd
from sqlalchemy import create_engine
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


# 根据indystry指标获取某个股票是否需要买入或者卖出
def get_indystry(engine_ts):
    # 积分器
    operator =0
    df = pd.read_sql("select * from stock_pool where (lastend + lastend*0.05) < cur_price and (lastend + lastend*0.101) > cur_price and description in \
                     (select tmptab.description from  (select description, count(*) aa from stock_pool \
                        where lastend + lastend*0.05 < cur_price and (lastend + lastend*0.101) > cur_price group by description order by aa desc limit 2) tmptab) \
                     ORDER BY (cur_price - lastend)/lastend asc", engine_ts)# 读入数据库中已有的行情表
    # print(df)

    print("正在获取所有股票的INDUSTRY...")
    for index, row in df.iterrows():
        if 'sh000001' == row["code"]:
            continue
        if 'sz399300' == row["code"]:
            continue
        # print("insert into stock_lib(code, insdt, metric, val) values('%s', '%s', '%s', '%s')" %(row["code"][2:9] +'.'+ row["code"][0:2].upper(), row["update_dt"].strftime('%Y%m%d'), "INDYSTRY", 10 + index))
        execMysqlCmd(tsdb, "insert into stock_lib(code, insdt, metric, val) values('%s', '%s', '%s', '%s')" %(row["code"][2:9] +'.'+ row["code"][0:2].upper(), row["update_dt"].strftime('%Y%m%d'), "INDYSTRY", 10 + index))
        # print("update stock_pool set vol4=1 where code='%s'" %(row["code"]))
        execMysqlCmd(tsdb, "update stock_pool set vol4=1 where code='%s'" %(row["code"]))  
        print("股票名：'%s'   值： '%s'" %(row["code"], 10 + index))
    print("获取所有股票的INDUSTRY完毕.")
    
    return True

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
        enddt = df.iat[df.shape[0]-1, 0]
        # enddt ='20220526'

    # 先判断是否需要增加当前行情数据一起分析
    df = pd.read_sql("select max(update_dt) update_dt from stock_pool", engine_ts)
     # 删除老数据
    execMysqlCmd(tsdb, "delete from stock_lib where insdt='%s' and metric='INDYSTRY'" %(df.iat[0, 0].strftime('%Y%m%d')))
    get_indystry(engine_ts)    
        
        
print("程序执行完毕！")       

   