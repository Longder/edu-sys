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
    grade_class_id_     bigint          null comment '关联班级id',
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

drop table if exists GRADE_CLASS;
create table GRADE_CLASS
(
    id_ bigint auto_increment,
    name_ varchar(255) null,
    description_ text null,
    constraint GRADE_CLASS_pk
        primary key (id_)
)
    comment '班级';


drop table if exists EXAM_PAPER;
create table EXAM_PAPER
(
    id_ bigint auto_increment,
    name_ varchar(255) null,
    grade_class_id_ bigint null,
    student_id_ bigint null,
    exam_type_ varchar(255) null,
    constraint exam_paper_pk
        primary key (id_)
)
    comment '试卷';

drop table if exists PAPER_DETAIL;
create table PAPER_DETAIL
(
    id_ bigint auto_increment,
    exam_paper_id_ bigint null,
    question_id_ bigint null,
    constraint paper_detail_pk
        primary key (id_)
)
    comment '试卷详情';

drop table if exists EXAM_RESULT;
create table EXAM_RESULT
(
    id_ bigint auto_increment,
    exam_paper_id_ bigint,
    score_ int,
    complete_time_ datetime,
    student_id_ bigint,
    constraint exam_result_pk
        primary key (id_)
)
    comment '考试结果';


drop table if exists CHAPTER;
create table CHAPTER
(
    id_ bigint auto_increment,
    subject_ varchar(255),
    name_ varchar(255),
    title_ varchar(255),
    content_ varchar(255),
    study_plan_ varchar(255),
        primary key (id_)
)
    comment '学科章节';


drop table if exists QUESTION;
create table QUESTION
(
    id_ BIGINT auto_increment comment '主键',
    content_ text null comment '题目内容',
    type_ varchar(100) null,
    score_ decimal null comment '分数',
    answer_ varchar(100) null,
    chapter_id_ bigint comment '试题所属章节',
    choice_A_ varchar(100),
    choice_B_ varchar(100),
    choice_C_ varchar(100),
    choice_D_ varchar(100),
    constraint QUESTION_pk
        primary key (id_)
)
    comment '题目';



insert into SYS_USER(name_, login_name_, password_,active_)
VALUES ('管理员', 'admin', '$2a$10$C5Gf4PUSLh2vjiiF1MsOFOuUjmFl7cdAbTHtMILfX.B0dPCj6aH3q',1);
insert into SYS_USER_ROLE(sys_user_id_, role_)
values (1, 'ROLE_ADMIN');