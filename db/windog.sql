/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50634
Source Host           : localhost:3306
Source Database       : yunbot

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2023-04-04 16:23:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alarm_msg
-- ----------------------------
DROP TABLE IF EXISTS `alarm_msg`;
CREATE TABLE `alarm_msg` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `indate` datetime DEFAULT NULL,
  `flag` int(11) DEFAULT '0',
  `msg` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=1750 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for bot_hold
-- ----------------------------
DROP TABLE IF EXISTS `bot_hold`;
CREATE TABLE `bot_hold` (
  `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `code` varchar(8) DEFAULT NULL COMMENT '股票代码',
  `cname` varchar(20) DEFAULT NULL COMMENT '股票名称',
  `amount` int(11) DEFAULT NULL COMMENT '数量',
  `price` decimal(16,2) DEFAULT NULL COMMENT '价格',
  `top_price` decimal(16,2) DEFAULT '0.00' COMMENT '最高价',
  `cur_date` datetime DEFAULT NULL COMMENT '交易时间',
  PRIMARY KEY (`tid`)
) ENGINE=MyISAM AUTO_INCREMENT=211395 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bot_info
-- ----------------------------
DROP TABLE IF EXISTS `bot_info`;
CREATE TABLE `bot_info` (
  `uid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名字',
  `nick` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `register_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册日期',
  `service_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '服务到期日',
  `grade` int(11) unsigned zerofill DEFAULT '00000000005' COMMENT '用户等级',
  `funds` decimal(16,2) DEFAULT NULL COMMENT '可用资金',
  `funds_init` decimal(16,2) DEFAULT NULL COMMENT '初始资金',
  `policy` int(10) unsigned zerofill DEFAULT NULL COMMENT '策略',
  `win` decimal(10,2) unsigned DEFAULT NULL COMMENT '止盈',
  `lost` decimal(10,2) unsigned DEFAULT NULL COMMENT '止损',
  `alarm` int(11) DEFAULT '0' COMMENT '触发信号',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=88020294 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bot_policy
-- ----------------------------
DROP TABLE IF EXISTS `bot_policy`;
CREATE TABLE `bot_policy` (
  `sid` int(11) NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT NULL,
  `value` decimal(4,2) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bot_policy_his
-- ----------------------------
DROP TABLE IF EXISTS `bot_policy_his`;
CREATE TABLE `bot_policy_his` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `curdt` datetime DEFAULT NULL,
  `funds_percent` decimal(4,2) DEFAULT NULL,
  `win` decimal(4,2) DEFAULT NULL,
  `lost` decimal(4,2) DEFAULT NULL,
  `policy` int(11) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=454 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for bot_profit
-- ----------------------------
DROP TABLE IF EXISTS `bot_profit`;
CREATE TABLE `bot_profit` (
  `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `nick` varchar(20) DEFAULT NULL COMMENT '昵称',
  `curdt` date DEFAULT NULL COMMENT '日期',
  `days` int(11) DEFAULT NULL COMMENT '累计天数',
  `benefit` decimal(8,4) DEFAULT NULL COMMENT '累计收益',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=224172 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for bot_profit5
-- ----------------------------
DROP TABLE IF EXISTS `bot_profit5`;
CREATE TABLE `bot_profit5` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `curdt` date DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `policy` int(11) DEFAULT NULL,
  `win` decimal(4,2) DEFAULT NULL,
  `lost` decimal(4,2) DEFAULT NULL,
  `benefit` decimal(8,4) DEFAULT NULL,
  `ord` int(11) DEFAULT '0',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=224508 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for bot_transaction
-- ----------------------------
DROP TABLE IF EXISTS `bot_transaction`;
CREATE TABLE `bot_transaction` (
  `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `code` varchar(8) DEFAULT NULL COMMENT '股票代码',
  `cname` varchar(20) DEFAULT NULL COMMENT '股票名称',
  `amount` decimal(16,0) DEFAULT NULL COMMENT '数量',
  `price` decimal(16,2) DEFAULT NULL COMMENT '卖出价',
  `in_price` decimal(16,2) DEFAULT NULL COMMENT '买入价',
  `in_date` datetime DEFAULT NULL COMMENT '买入日期',
  `cur_date` datetime DEFAULT NULL COMMENT '卖出日期',
  `type` int(16) DEFAULT NULL COMMENT '原因',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=198788 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stock_all
-- ----------------------------
DROP TABLE IF EXISTS `stock_all`;
CREATE TABLE `stock_all` (
  `stock_code` varchar(45) NOT NULL COMMENT '代码',
  `state_dt` varchar(45) NOT NULL COMMENT '交易日期',
  `open` double DEFAULT NULL COMMENT '开盘价',
  `high` decimal(20,2) DEFAULT NULL COMMENT '最高价',
  `low` decimal(20,2) DEFAULT NULL COMMENT '最低价',
  `close` decimal(20,2) DEFAULT NULL COMMENT '收盘价',
  `pre_close` decimal(20,2) DEFAULT NULL COMMENT '前日收盘',
  `amt_change` decimal(20,2) DEFAULT NULL COMMENT '涨跌',
  `pct_change` decimal(20,2) DEFAULT NULL COMMENT '幅度',
  `vol` int(20) DEFAULT NULL COMMENT '成交量',
  `amount` decimal(30,2) unsigned DEFAULT NULL COMMENT '成交金额',
  PRIMARY KEY (`stock_code`,`state_dt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for stock_basic
-- ----------------------------
DROP TABLE IF EXISTS `stock_basic`;
CREATE TABLE `stock_basic` (
  `ts_code` text,
  `symbol` text,
  `name` text,
  `area` text,
  `industry` text,
  `market` text,
  `list_date` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for stock_cal
-- ----------------------------
DROP TABLE IF EXISTS `stock_cal`;
CREATE TABLE `stock_cal` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `opendt` varchar(10) DEFAULT NULL COMMENT '交易日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for stock_daily
-- ----------------------------
DROP TABLE IF EXISTS `stock_daily`;
CREATE TABLE `stock_daily` (
  `ts_code` varchar(10) NOT NULL COMMENT '代码',
  `trade_date` varchar(8) NOT NULL COMMENT '交易日期',
  `open` double DEFAULT NULL COMMENT '开盘价',
  `high` double DEFAULT NULL COMMENT '最高价',
  `low` double DEFAULT NULL COMMENT '最低价',
  `cls` double DEFAULT NULL COMMENT '收盘价',
  `pre_close` double DEFAULT NULL COMMENT '前日收盘',
  `chg` double DEFAULT NULL COMMENT '涨跌',
  `pct_chg` double DEFAULT NULL COMMENT '幅度',
  `vol` double DEFAULT NULL COMMENT '成交量',
  `amount` double DEFAULT NULL COMMENT '成交金额',
  PRIMARY KEY (`ts_code`,`trade_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for stock_industry
-- ----------------------------
DROP TABLE IF EXISTS `stock_industry`;
CREATE TABLE `stock_industry` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(50) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=13063 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for stock_lib
-- ----------------------------
DROP TABLE IF EXISTS `stock_lib`;
CREATE TABLE `stock_lib` (
  `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `code` varchar(10) DEFAULT NULL COMMENT '股票代码',
  `insdt` varchar(14) DEFAULT NULL COMMENT '日期',
  `metric` varchar(10) DEFAULT NULL COMMENT '指标名',
  `val` int(11) DEFAULT NULL COMMENT '指标值',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=883216 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for stock_pool
-- ----------------------------
DROP TABLE IF EXISTS `stock_pool`;
CREATE TABLE `stock_pool` (
  `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `code` varchar(8) NOT NULL DEFAULT '' COMMENT '代码',
  `cname` varchar(20) DEFAULT NULL COMMENT '股票名称',
  `grade` int(11) DEFAULT '1' COMMENT '评级',
  `act` int(11) DEFAULT '0' COMMENT '动作',
  `update_dt` datetime DEFAULT NULL COMMENT '更新日期',
  `cur_price` decimal(16,2) DEFAULT '0.00' COMMENT '最新价格',
  `vbeg` decimal(16,2) DEFAULT NULL COMMENT '开盘价',
  `vend` decimal(16,2) DEFAULT NULL COMMENT '收盘价',
  `lastend` decimal(16,2) DEFAULT '0.00' COMMENT '昨收盘',
  `vhigh` decimal(16,2) DEFAULT NULL COMMENT '最高',
  `vlow` decimal(16,2) DEFAULT NULL COMMENT '最低',
  `vtotal` decimal(16,2) DEFAULT NULL COMMENT '成交量',
  `vamount` decimal(16,2) DEFAULT NULL COMMENT '成交金额',
  `vol1` int(11) DEFAULT NULL,
  `vol2` int(11) DEFAULT NULL,
  `vol3` int(11) DEFAULT NULL,
  `vol4` int(11) DEFAULT NULL,
  `vol5` int(11) DEFAULT NULL,
  `vol6` int(11) DEFAULT NULL,
  `vol7` int(11) DEFAULT NULL,
  `vol8` int(11) DEFAULT NULL,
  `vol9` int(11) DEFAULT NULL,
  `vol10` int(11) DEFAULT NULL,
  `vol11` int(11) DEFAULT NULL,
  `vol12` int(11) DEFAULT NULL,
  `vol13` int(11) DEFAULT NULL,
  `vol14` int(11) DEFAULT NULL,
  `vol15` int(11) DEFAULT NULL,
  `list` varchar(50) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`sid`),
  UNIQUE KEY `code_index` (`code`)
) ENGINE=MyISAM AUTO_INCREMENT=5186 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stock_quotes
-- ----------------------------
DROP TABLE IF EXISTS `stock_quotes`;
CREATE TABLE `stock_quotes` (
  `code` varchar(8) NOT NULL,
  `curdt` varchar(8) NOT NULL,
  `curtm` varchar(4) NOT NULL,
  `vopen` float(16,2) DEFAULT NULL,
  `vcurrent` float(16,2) DEFAULT NULL,
  `vlastclose` float(16,2) DEFAULT NULL,
  `vhigh` float(16,2) DEFAULT NULL,
  `vlow` float(16,2) DEFAULT NULL,
  `vtotal` int(11) DEFAULT NULL,
  `vamount` float(16,2) DEFAULT NULL,
  PRIMARY KEY (`code`,`curdt`,`curtm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_sys_datas
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_datas`;
CREATE TABLE `t_sys_datas` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `file_path` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件地址',
  `file_absolute_path` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '绝对路径',
  `file_suffix` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '后缀',
  `file_type` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'Y 项目目录，N盘符目录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文件表存储表';

-- ----------------------------
-- Table structure for t_sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dict_data`;
CREATE TABLE `t_sys_dict_data` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT '' COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT '' COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Table structure for t_sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dict_type`;
CREATE TABLE `t_sys_dict_type` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_type` (`dict_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Table structure for t_sys_email
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_email`;
CREATE TABLE `t_sys_email` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `receivers_email` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '接收人电子邮件',
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '邮件标题',
  `content` text COLLATE utf8_bin COMMENT '内容',
  `send_user_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '发送人id',
  `send_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '发送人账号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='电子邮件';

-- ----------------------------
-- Table structure for t_sys_file
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_file`;
CREATE TABLE `t_sys_file` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片名字',
  `create_user_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人id',
  `create_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人名字',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `update_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人名字',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文件信息表';

-- ----------------------------
-- Table structure for t_sys_file_data
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_file_data`;
CREATE TABLE `t_sys_file_data` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `data_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '数据id',
  `file_id` varchar(255) COLLATE utf8_bin DEFAULT '文件id' COMMENT '文件id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文件数据外键绑定表';

-- ----------------------------
-- Table structure for t_sys_inter_url
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_inter_url`;
CREATE TABLE `t_sys_inter_url` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `inter_name` varchar(255) DEFAULT NULL COMMENT '拦截名称',
  `url` varchar(255) DEFAULT NULL COMMENT '拦截url',
  `type` int(2) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拦截url表';

-- ----------------------------
-- Table structure for t_sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_notice`;
CREATE TABLE `t_sys_notice` (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `type` int(5) DEFAULT NULL COMMENT '类型',
  `create_id` varchar(255) DEFAULT NULL COMMENT '创建人id',
  `create_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人name',
  `create_time` datetime DEFAULT NULL COMMENT '发信时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告';

-- ----------------------------
-- Table structure for t_sys_notice_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_notice_user`;
CREATE TABLE `t_sys_notice_user` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `notice_id` varchar(255) DEFAULT NULL COMMENT '公告id',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `state` int(2) DEFAULT NULL COMMENT '0未阅读 1 阅读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告_用户外键';

-- ----------------------------
-- Table structure for t_sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_oper_log`;
CREATE TABLE `t_sys_oper_log` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `method` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '方法',
  `oper_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '操作人',
  `oper_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'url',
  `oper_param` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '参数',
  `error_msg` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `oper_time` date DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='日志记录表';

-- ----------------------------
-- Table structure for t_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission`;
CREATE TABLE `t_sys_permission` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `descripion` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `url` varchar(255) DEFAULT NULL COMMENT '授权链接',
  `is_blank` int(255) DEFAULT '0' COMMENT '是否跳转 0 不跳转 1跳转',
  `pid` varchar(255) DEFAULT NULL COMMENT '父节点id',
  `perms` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `visible` int(255) DEFAULT NULL COMMENT '是否可见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Table structure for t_sys_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission_role`;
CREATE TABLE `t_sys_permission_role` (
  `id` varchar(255) NOT NULL,
  `role_id` varchar(255) DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(255) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限中间表';

-- ----------------------------
-- Table structure for t_sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_quartz_job`;
CREATE TABLE `t_sys_quartz_job` (
  `id` varchar(255) NOT NULL COMMENT '日志id',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务组名',
  `invoke_target` varchar(255) DEFAULT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron执行表达式',
  `misfire_policy` varchar(255) DEFAULT NULL COMMENT 'cron计划策略',
  `concurrent` varchar(255) DEFAULT NULL COMMENT '是否并发执行（0允许 1禁止）',
  `status` int(11) DEFAULT NULL COMMENT '任务状态（0正常 1暂停）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

-- ----------------------------
-- Table structure for t_sys_quartz_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_quartz_job_log`;
CREATE TABLE `t_sys_quartz_job_log` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务组名',
  `invoke_target` varchar(255) DEFAULT NULL COMMENT '调用目标字符串',
  `job_message` varchar(255) DEFAULT NULL COMMENT '日志信息',
  `status` int(11) DEFAULT NULL COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(255) DEFAULT NULL COMMENT '异常信息',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for t_sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_user`;
CREATE TABLE `t_sys_role_user` (
  `id` varchar(255) NOT NULL,
  `sys_user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `sys_role_id` varchar(255) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色中间表';

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户账号',
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户密码',
  `nickname` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Table structure for user_hold
-- ----------------------------
DROP TABLE IF EXISTS `user_hold`;
CREATE TABLE `user_hold` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `code` varchar(8) DEFAULT NULL,
  `cname` varchar(20) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `price` decimal(16,2) DEFAULT NULL,
  `top_price` decimal(16,2) DEFAULT '0.00',
  `cur_date` datetime DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=MyISAM AUTO_INCREMENT=2666 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `uid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `wxid` varchar(50) DEFAULT NULL COMMENT '微信id',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名字',
  `nick` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `register_dt` date DEFAULT NULL COMMENT '注册日期',
  `service_dt` date DEFAULT NULL COMMENT '服务到期日',
  `grade` int(11) unsigned zerofill DEFAULT NULL COMMENT '用户等级',
  `funds` decimal(16,2) DEFAULT NULL COMMENT '可用资金',
  `funds_init` decimal(16,2) DEFAULT NULL COMMENT '初始资金',
  `mail` varchar(50) DEFAULT NULL COMMENT '邮件',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机',
  `qq` decimal(16,0) DEFAULT NULL COMMENT 'QQ号码，系统提醒用',
  `alarm` int(11) DEFAULT '0',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=80000004 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_profit
-- ----------------------------
DROP TABLE IF EXISTS `user_profit`;
CREATE TABLE `user_profit` (
  `uid` int(11) NOT NULL,
  `curdt` date NOT NULL,
  `profit` decimal(4,2) NOT NULL,
  PRIMARY KEY (`uid`,`curdt`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for user_transaction
-- ----------------------------
DROP TABLE IF EXISTS `user_transaction`;
CREATE TABLE `user_transaction` (
  `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `code` varchar(8) DEFAULT NULL,
  `cname` varchar(20) DEFAULT NULL,
  `amount` decimal(16,0) DEFAULT NULL COMMENT '数量',
  `price` decimal(16,2) DEFAULT NULL COMMENT '买入价格',
  `in_price` decimal(16,2) DEFAULT NULL,
  `in_date` datetime DEFAULT NULL,
  `cur_date` datetime DEFAULT NULL COMMENT '买入日期',
  `type` int(16) DEFAULT NULL COMMENT '盈亏',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=2506 DEFAULT CHARSET=utf8;

-- ----------------------------
-- View structure for bot_hold_view
-- ----------------------------
DROP VIEW IF EXISTS `bot_hold_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `bot_hold_view` AS select `a`.`uid` AS `uid`,`a`.`code` AS `code`,`a`.`cname` AS `cname`,`a`.`amount` AS `amount`,`a`.`price` AS `price`,`a`.`top_price` AS `top_price`,`b`.`cur_price` AS `cur_price`,((100 * (`b`.`cur_price` - `a`.`price`)) / `a`.`price`) AS `benefit`,`a`.`cur_date` AS `cur_date` from (`bot_hold` `a` join `stock_pool` `b`) where (`a`.`code` = `b`.`code`) order by `a`.`uid`,`a`.`cur_date` ;

-- ----------------------------
-- View structure for bot_profit_view
-- ----------------------------
DROP VIEW IF EXISTS `bot_profit_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `bot_profit_view` AS select `a`.`uid` AS `uid`,`a`.`nick` AS `nick`,curdate() AS `curdt`,timestampdiff(DAY,`a`.`register_dt`,curdate()) AS `days`,((((`a`.`funds` + sum((`b`.`amount` * `c`.`cur_price`))) - `a`.`funds_init`) / `a`.`funds_init`) * 100) AS `svalue` from ((`bot_info` `a` join `bot_hold` `b`) join `stock_pool` `c`) where ((`a`.`uid` = `b`.`uid`) and (`b`.`code` = `c`.`code`)) group by `a`.`uid`,`a`.`nick` order by `svalue` desc ;

-- ----------------------------
-- View structure for user_hold_view
-- ----------------------------
DROP VIEW IF EXISTS `user_hold_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_hold_view` AS select `a`.`uid` AS `uid`,`a`.`code` AS `code`,`a`.`cname` AS `cname`,`a`.`amount` AS `amount`,`a`.`price` AS `price`,`a`.`top_price` AS `top_price`,`b`.`cur_price` AS `cur_price`,((100 * (`b`.`cur_price` - `a`.`price`)) / `a`.`price`) AS `benefit`,`a`.`cur_date` AS `cur_date` from (`user_hold` `a` join `stock_pool` `b`) where (`a`.`code` = `b`.`code`) order by `a`.`uid`,`a`.`cur_date` ;

-- ----------------------------
-- View structure for user_profit_view
-- ----------------------------
DROP VIEW IF EXISTS `user_profit_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `user_profit_view` AS select `a`.`uid` AS `uid`,`a`.`nick` AS `nick`,curdate() AS `curdt`,timestampdiff(DAY,`a`.`register_dt`,curdate()) AS `days`,((((`a`.`funds` + sum((`b`.`amount` * `c`.`cur_price`))) - `a`.`funds_init`) / `a`.`funds_init`) * 100) AS `svalue` from ((`user_info` `a` join `user_hold` `b`) join `stock_pool` `c`) where ((`a`.`uid` = `b`.`uid`) and (`b`.`code` = `c`.`code`)) group by `a`.`uid`,`a`.`nick` order by `svalue` desc ;
