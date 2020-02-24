CREATE DATABASE edu_sys DEFAULT CHARACTER SET UTF8;

USE edu_sys;

drop table if exists SYS_USER;
create table SYS_USER
(
    id_         bigint auto_increment comment '主键',
    name_       varchar(255) null comment '姓名',
    login_name_ varchar(100) null comment '登录名',
    password_   varchar(100) null comment '密码',
    email_     varchar(100)  null comment '邮箱',
    active_     bit          null comment '有效性',
        constraint SYS_USER_pk
        primary key (id_)
)
    comment '系统用户表';

drop table if exists SYS_USER_ROLE;
create table SYS_USER_ROLE
(
    id_          bigint auto_increment comment '主键',
    sys_user_id_ BIGINT      null comment '关联用户表主键',
    role_        varchar(50) null comment '角色名',
    constraint SYS_USER_ROLE_pk
        primary key (id_)
)
    comment '用户角色表';

insert into SYS_USER(name_, login_name_, password_)
VALUES ('管理员', 'admin', '$2a$10$C5Gf4PUSLh2vjiiF1MsOFOuUjmFl7cdAbTHtMILfX.B0dPCj6aH3q');
insert into SYS_USER_ROLE(sys_user_id_, role_)
values (1, 'ROLE_ADMIN');