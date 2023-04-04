import MySQLdb
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


if __name__ == '__main__':

    # 读取配置文件
    conf = YbConfig.InitConfig().dic

    # 设置访问api的key
    pro = ts.pro_api(conf['token'])

    # # 打开数据库连接
    engine_ts = create_engine(conf['url'])
    tsdb = MySQLdb.connect(conf['ip'], conf['user'], conf['pwd'], "windog", charset='utf8')

    df = pro.query('stock_basic', exchange='', list_status='L', fields='ts_code,name,industry')
    for index, row in df.iterrows():
        execMysqlCmd("update stock_pool set description='%s' where code='%s'" % (
            row['industry'], row['ts_code'][7:9].lower() + row['ts_code'][0:6]))

    print('更新行业成功！')
