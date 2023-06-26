-- ----------------------------
-- 演示表
-- ----------------------------
drop table if exists t_demo;
create table t_demo
(
    id          bigint(20) not null comment '主键id',
    name        varchar(100) comment '名称',

    del_flag    char(1)     default '0' comment '删除标志（0代表存在 2代表删除）',
    create_by   varchar(64) default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64) default '' comment '更新者',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb comment = '演示表';
-- ----------------------------
-- 指令表
-- ----------------------------
drop table if exists t_instruct;
create table t_instruct
(
    id           varchar(32) primary key comment '指令id',
    instruct     varchar(100) comment '指令',
    expectant    varchar(200) comment '预取的结果',
    instruct_set varchar(32) not null comment '指令集合id',
    foreign key (instruct_set) references t_instruct_set (id)
) engine = innodb comment = '指令';
-- ----------------------------
-- 指令集合表
-- ----------------------------
drop table if exists t_instruct_set;
create table t_instruct_set
(
    id varchar(32) primary key comment '指令id'
) engine = innodb comment = '指令集合';
drop table if exists t_manage;
create table t_manage
(
    id           varchar(32) primary key comment '指令id',
    username     varchar(100) comment '指令',
    password     varchar(200) comment '预取的结果',
    status varchar(2) comment 'xxx',
    lead varchar(32) comment 'xxxx'
) engine = innodb comment = '管理者';
