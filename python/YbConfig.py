# -*- coding:utf8 -*-
# 从yml文件中读取数据库及其他配置的基础类
# 读取的配置信息保存在dict中
#
# Writen by William
# 2023-02-03
# Ver 1.0

import os
import sys
import yaml


class InitConfig(object):
    def __init__(self):
        self.dic = {}
        ret = self.get_config()

    def get_config(self):
        # 读取数据库配置文件
        if 2 > len(os.path.dirname(sys.argv[0])):
            db = os.getcwd() + "/../application-prod.yml"
        else:
            db = os.path.dirname(sys.argv[0]) + "/../application-prod.yml"
        with open(db, 'r', encoding='utf-8') as f:
            result = yaml.load(f.read(), Loader=yaml.FullLoader)
        ip = result["spring"]["datasource"]["druid"]["master"]["ip"]
        port = result["spring"]["datasource"]["druid"]["master"]["port"]
        user = result["spring"]["datasource"]["druid"]["master"]["username"]
        pwd = result["spring"]["datasource"]["druid"]["master"]["password"]
        self.dic.update({'ip': ip})
        self.dic.update({'port': port})
        self.dic.update({'user': user})
        self.dic.update({'pwd': pwd})
        self.dic.update({'url': 'mysql://%s:%s@%s:%s/windog?charset=utf8&use_unicode=1' % (user, pwd, ip, port)})

        # 读取基本配置文件，获取令牌
        if 2 > len(os.path.dirname(sys.argv[0])):
            conf = os.getcwd() + "/../application.yml"
        else:
            conf = os.path.dirname(sys.argv[0]) + "/../application.yml"
        with open(conf, 'r', encoding='utf-8') as f:
            result = yaml.load(f.read(), Loader=yaml.FullLoader)
        token = result["fuce"]["token"]
        self.dic.update({'token': token})
        return True
