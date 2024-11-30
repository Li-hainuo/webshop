drop
    database if exists `webshop`;
create
    database if not exists `webshop` default character set utf8mb4;
use
    `webshop`;
-- 用户表
CREATE TABLE `t_user`
(
    `id`          char(20)    NOT NULL DEFAULT '' COMMENT 'id 1为管理员',
    `username`    varchar(60) NOT NULL COMMENT '用户名',
    `password`    varchar(60) NOT NULL COMMENT '密码',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    `is_deleted`  tinyint(2)  NOT NULL DEFAULT '0' COMMENT '逻辑删除：0：未删除 1：已删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';
