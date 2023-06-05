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
