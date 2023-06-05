############ 一级菜单 ############
-- 首页管理
insert into sys_menu
values ('4', '首页管理', '0', '4', 'homeManagement', null, '', 1, 0, 'M', '0', '0', '', 'list', 'admin', sysdate(),
        '', null, '首页管理目录');

-- 演示 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible,
                      status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1665531319886716929, '演示', '4', '1', 'demo', 'business/demo/index', 1, 0, 'C', '0', '0', 'business:demo:list',
        '#', 'admin', sysdate(), '', null, '演示菜单');

-- 演示 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible,
                      status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1665531319886716930, '演示查询', 1665531319886716929, '1', '#', '', 1, 0, 'F', '0', '0', 'business:demo:query', '#',
        'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible,
                      status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1665531319886716931, '演示新增', 1665531319886716929, '2', '#', '', 1, 0, 'F', '0', '0', 'business:demo:add', '#',
        'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible,
                      status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1665531319886716932, '演示修改', 1665531319886716929, '3', '#', '', 1, 0, 'F', '0', '0', 'business:demo:edit', '#',
        'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible,
                      status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1665531319886716933, '演示删除', 1665531319886716929, '4', '#', '', 1, 0, 'F', '0', '0', 'business:demo:remove',
        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible,
                      status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1665531319886716934, '演示导出', 1665531319886716929, '5', '#', '', 1, 0, 'F', '0', '0', 'business:demo:export',
        '#', 'admin', sysdate(), '', null, '');
