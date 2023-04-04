import os

import flask
import pandas as pd
import redis
from flask import request
from sqlalchemy import create_engine
import YbConfig

# 创建一个服务，把当前这个python文件当做一个服务
server = flask.Flask(__name__)

server.config['JSON_AS_ASCII'] = False


# http://127.0.0.1:5000/getRTQuotes?stocklist=sz300037,sh600031
@server.route('/getRTQuotes', methods=['get'])
def getRTQuotes():
    lststocks = request.values.get('stocklist')
    # 可指定股票代码
    if None != lststocks:
        stocklst = lststocks.split(',')
    else:
        # 打开数据库连接
        engine_ts = create_engine(conf['url'])
        df_code = pd.read_sql('select code from stock_pool', engine_ts)
        stocklst = df_code.values

    # 从redis中获取数据
    redis_conn = redis.Redis(host='127.0.0.1', port=6379, db=0, decode_responses=True)

    dicStock = {}
    for item in stocklst:
        # 转换从数据集获取的list，例如['bj430047']
        code = str(item)
        if (len(code) > 8):
            code = code[2:10]
        # 直接带参数的不需要转换
        value = redis_conn.get(code)
        if (None != value):
            dicStock.update({code: value})

    if (0 < len(dicStock)):
        sret = str(dicStock)
    else:
        sret = ''
    ret = {
        "msg": "查询成功",
        "status": 200,
        "data": sret
    }
    return ret

# http://127.0.0.1:5000/getHistoryQuotes?datetime=202303291440
@server.route('/getHistoryQuotes', methods=['get'])
def getHistoryQuotes():
    params = request.values.get('datetime')
    curdt = params[0:8]
    curtm = params[8:12]
    sret = '';
    if (12 == len(params)):
        if os.path.exists(os.getcwd() + "/../data/" + curdt + '.dat'):
            f = open(os.getcwd() + "/../data/" + curdt + '.dat', "r", encoding="UTF-8")
            line = f.readline()
            while line:
                if (curtm == line[0:4]):
                    sret = line[5:len(line) - 1].strip()
                    break
                line = f.readline()
            if ('' == sret):
                sret = '该时刻的行情数据没有找到:' + curtm
        else:
            sret = '该日期的行情数据文件没有找到:' + os.getcwd() + "/../data/" + curdt + '.dat'

    else:
        ret = {
            "msg": "查询失败，参数必须为 202302221302 格式！",
            "status": 3003,
            "data": ''
        }
        return ret

    ret = {
        "msg": "查询成功",
        "status": 200,
        "data": sret
    }
    return ret


if __name__ == '__main__':
    # 读取配置文件
    conf = YbConfig.InitConfig().dic

    server.run(host='0.0.0.0', port=5000, debug=True)
