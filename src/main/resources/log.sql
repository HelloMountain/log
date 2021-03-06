
-- ----------------------------
-- 用户表
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `name` varchar (32) DEFAULT NULL COMMENT '真实姓名',
  `password` varchar (32) DEFAULT NULL COMMENT '密码',
  `role` varchar (32) DEFAULT "user" NOT NULL COMMENT '角色',
  `avatar` text DEFAULT NULL COMMENT '头像',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  `published` tinyint(1) DEFAULT 1 COMMENT '是否发布',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into user values (null, 'admin', '青山', '123456','user' ,'https://raw.githubusercontent.com/wiki/HelloMountain/ImageBed/vue/avatar.jpg', now(), now(),1);

-- ----------------------------
-- 权限表
-- ----------------------------
DROP TABLE IF EXISTS  `privilege`;
CREATE TABLE `privilege`(
  `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` VARCHAR (32) NOT NULL COMMENT '用户id',
  `nginx_id` VARCHAR (32) NOT NULL COMMENT '服务器id',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  `published` tinyint(1) DEFAULT 1 COMMENT '是否发布',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT ChARSET=utf8;

insert into privilege values (null, '1', '1');


-- ----------------------------
-- 服务器表
-- ----------------------------
DROP TABLE IF EXISTS  `server`;
CREATE TABLE `server` (
  `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `uuid` varchar (32) unique NOT NULL COMMENT '全局唯一标识符',
  `name` varchar (32) unique NOT NULL COMMENT '服务器名称',
  `host` varchar (32) NOT NULL COMMENT '主机',
  `log_path` text COMMENT '日志地址',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  `published` tinyint(1) DEFAULT 1 COMMENT '是否发布',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into server values (null, REPLACE(uuid(), '-', ''), '111');

-- ----------------------------
-- 项目表
-- ----------------------------
DROP TABLE IF EXISTS `sys_project`;
CREATE TABLE `sys_project` (
  `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `uuid`  varchar(32) unique NULL COMMENT '项目编号',
  `name`  varchar(32) unique NOT NULL COMMENT '项目名称',
  `remark` text DEFAULT NULL COMMENT '备注',
  `user_id` int(11) DEFAULT NULL comment '用户ID',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  `published` tinyint(1) DEFAULT 1 COMMENT '是否发布',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- log字段
-- ----------------------------

DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `timestamp` datetime NOT NULL COMMENT '时间',
  `version` varchar(32) NOT NULL COMMENT '版本号',
  `client` varchar(32) NOT NULL COMMENT '客户端请求地址',
  `url` text NOT NULL COMMENT '请求中的当前URI',
  `status` int(11) NOT NULL COMMENT '请求状态',
  `domian` varchar(32) NOT NULL COMMENT '请求地址',
  `host` varchar(32) NOT NULL COMMENT 'HTTP请求行的主机名>HOST 请求头字段>',
  `size` varchar(32) NOT NULL COMMENT '文件内容大小',
  `responsetime` varchar(32) NOT NULL COMMENT '处理客户端请求使用的时间,单位为秒',
  `referer` varchar(32) NOT NULL COMMENT '哪个页面链接访问过来的',
  `ua` text COMMENT '客户端浏览器相关信息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
-- ----------------------------
-- 浏览器
-- ----------------------------

DROP TABLE IF EXISTS `browser`;
CREATE TABLE `browser` (
  `id` int (11) NOT NULL unique AUTO_INCREMENT COMMENT '主键id',
  `browser`  varchar(32)  NOT NULL COMMENT '浏览器名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- 国内分布
-- ----------------------------
DROP TABLE IF EXISTS `addr`;
CREATE TABLE `addr` (
  `id` int (11) NOT NULL unique AUTO_INCREMENT COMMENT '主键id',
  `log_id` int (11) NOT NULL COMMENT 'log id',
  `ip` VARCHAR (32) NOT NULL COMMENT 'ip',
  `addr`  varchar(32)  NOT NULL COMMENT '国内位置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- 任务表
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `id` INT (11) NOT NULL unique AUTO_INCREMENT COMMENT '主键id',
  `uuid`  varchar(32)  NULL COMMENT '编号',
  `project_id` int(11) NOT NULL COMMENT '项目编号',
  `name`  varchar(32) NOT NULL COMMENT '任务名称',
  `remark` text DEFAULT NULL COMMENT '备注',
  `type` varchar(32) default 'nginx2hive' COMMENT '类型:nginx2hive,mysql2hive',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  `published` tinyint(1) DEFAULT 1 COMMENT '是否发布',
  PRIMARY KEY (`project_id`, `name`),
  FOREIGN KEY (`project_id`) REFERENCES `sys_project` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- ----------------------------
-- 任务设置 nginx_hive
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_nginx2hive`;
CREATE TABLE `sys_job_nginx2hive` (
  `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `job_id` int(11) NOT NULL COMMENT '任务编号',
  `file_path` varchar(50)  NULL comment '日志路径',
  `file_format` varchar(200)  NULL comment '日志格式',
  `demo_data` text  NULL comment '样例数据',
  `flink_parallel` varchar(50) NULL comment 'flink并行数',
  `hive_database` varchar(50) NULL comment 'hive库名',
  `hive_table` varchar(50) NULL comment 'hive表名',
  `hive_offset` varchar(15) NULL default "0" comment 'hive结果表offset',
  `columns` text NULL comment '列解析',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`job_id`) REFERENCES `sys_job` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- 任务设置 mysql_hive
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_mysql2hive`;
CREATE TABLE `sys_job_mysql2hive` (
  `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `job_id` int(11) NOT NULL COMMENT '任务编号',
  `mysql_host` varchar(50)  NULL comment 'mysql主机',
  `mysql_port` int  NULL comment 'mysql端口',
  `mysql_username` varchar(50)  NULL comment 'mysql用户名',
  `mysql_password` varchar(50)  NULL comment 'mysql密码',
  `tables` text  NULL comment '表信息串',
  `table_list` text  NULL comment '需要导入的全部列表',
  `table_field` text  NULL comment '表字段信息json串',
  `flink_parallel` varchar(50) NULL comment 'flink并行数',
  `hive_database` varchar(50) NULL comment 'hive库名',
  `sqoop_sum` int default 0 comment '同步表的总数',
  `sqoop_current` int default 0 comment '同步表当前索引',
  `sqoop_flag` int default 0 comment '任务执行情况',
  `flink_flag` int default 0 comment 'flink执行情况 0-未启动，1-启动，2-启动异常',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`job_id`) REFERENCES `sys_job` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- flume report
-- ----------------------------
DROP TABLE IF EXISTS  `flume_report`;
CREATE TABLE `flume_report` (
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `uuid` varchar (50) NOT NULL comment '编号',
    `report` text comment 'flume报告',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

