-- 有道翻译 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1676170443814133761, '有道翻译', '3', '1', 'youdaoapi', 'business/youdaoapi/index', 1, 0, 'C', '0', '0', 'business:youdaoapi:list', '#', 'admin', sysdate(), '', null, '有道翻译菜单');

-- 有道翻译 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1676170443814133762, '有道翻译查询', 1676170443814133761, '1',  '#', '', 1, 0, 'F', '0', '0', 'business:youdaoapi:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1676170443814133763, '有道翻译新增', 1676170443814133761, '2',  '#', '', 1, 0, 'F', '0', '0', 'business:youdaoapi:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1676170443814133764, '有道翻译修改', 1676170443814133761, '3',  '#', '', 1, 0, 'F', '0', '0', 'business:youdaoapi:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1676170443814133765, '有道翻译删除', 1676170443814133761, '4',  '#', '', 1, 0, 'F', '0', '0', 'business:youdaoapi:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1676170443814133766, '有道翻译导出', 1676170443814133761, '5',  '#', '', 1, 0, 'F', '0', '0', 'business:youdaoapi:export',       '#', 'admin', sysdate(), '', null, '');
drop table if exists youdaoapi;
create table youdaoapi
(
    youdao_key   varchar(50)  not null comment 'key',
    youdao_secret varchar(50)   not null comment '密钥',
    url varchar(100)    not null  default 'https://openapi.youdao.com/api'    comment  '链接的api接口',
    primary key (youdao_key)
)engine = innodb comment = '有道翻译表'
