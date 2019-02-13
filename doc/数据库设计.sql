drop table if exists user_info;
create table user_info
(
  id           int(11) auto_increment primary key,
  login_time   datetime default CURRENT_TIMESTAMP null,
  state        int(1)  default 0 comment '状态',
  level        int(1)  default 1 comment '级别',
  user_name    varchar(500)                       null comment '用户名',
  actual_name  varchar(100)                       null comment '真实姓名',
  phone        varchar(11)                        null comment '手机号',
  new_password varchar(50)                        null comment '新密码',
  old_password varchar(50)                        null comment '旧密码',
  articles_num varchar(500)                       null comment '文章数',
  create_time  datetime default CURRENT_TIMESTAMP null,
  update_time  datetime default CURRENT_TIMESTAMP null,
  del          tinyint default 0 comment '删除 0 false 未删， 1 true 已删'
) comment '用户信息表';

drop table if exists user_login;
create table user_login
(
  id          int(11)      auto_increment primary key,
  uid         int(11)                            null comment '用户名',
  ip          varchar(100)                       null comment '真实姓名',
  phone       varchar(11)                        null comment '手机号',
  state       int(1)       default 0 comment '状态0.成功 1.失败',
  reason      varchar(200) default 0 comment '原因',
  create_time datetime default CURRENT_TIMESTAMP null,
  del         tinyint      default 0 comment '删除 0 false 未删， 1 true 已删'
) comment '用户登录记录表';


drop table if exists system_info;
create table system_info
(
  id               int(11) auto_increment primary key,
  server_software  varchar(100) null comment '服务器软件',
  java_version     varchar(50)  null comment 'java版本',
  operating_system varchar(50)  null comment '操作系统',
  java_mode        varchar(500) null comment 'java运行方式',
  mysql_version    varchar(500) null comment 'mysql版本',
  program_version  varchar(500) null comment '程序版本',
  program_code     varchar(500) null comment '程序编码',
  copyright        varchar(500) null comment '版权所有'

) comment '系统信息';

drop table if exists article_label;
create table article_label
(
  id   int(11) auto_increment primary key,
  name varchar(100) not null comment '标签',
  uid  int(11)      not null comment '用户id'
) comment '文章标签';

drop table if exists fk_article_label;
create table fk_article_label
(
  id         int(11) auto_increment primary key,
  label_id   int(11) not null comment '标签id',
  article_id int(11) not null comment '文章id'
) comment '文章和标签关系';

drop table if exists article_info;
create table article_info
(
  id            int(11) auto_increment primary key,
  uid           int(11)                               not null comment '用户id',
  title         varchar(100)                          null comment '标题',
  title_pic     varchar(500)                          null comment '标题图片',
  content       varchar(2000)                         null comment '详细内容',
  keyword       varchar(50)                           null comment '关键词',
  description   varchar(500)                          null comment '描述',
  state         int(1)  default 0 comment '状态 0.正常 1.异常',
  release_state varchar(50)                           null comment '发布状态 0.未发布 1.已发布',
  openness      int(1) default 0                      null comment '公开度 0公开 1.加密',
  create_time   datetime default CURRENT_TIMESTAMP    null,
  update_time   datetime default CURRENT_TIMESTAMP    null,
  del           tinyint default 0 comment '删除 0 false 未删， 1 true 已删'
) comment '文章信息';


drop table if exists article_comment;
create table article_comment
(
  id              int(11) auto_increment primary key,
  uid             int(11)                            null comment '用户id',
  article_info_id int(11)                            null comment '文章信息id',
  content         varchar(2000)                      null comment '评论内容',
  state           int(1)  default 0 comment '状态0 正常 1.异常',
  create_time     datetime default CURRENT_TIMESTAMP null,
  update_time     datetime default CURRENT_TIMESTAMP null,
  del             tinyint default 0 comment '删除 0 false 未删， 1 true 已删'
) comment '文章评论';

drop table if exists article_read;
create table article_read
(
  id              int(11) auto_increment primary key,
  uid             int(11)                            null comment '用户id',
  article_info_id int(11)                            null comment '文章信息id',
  state           int(1)  default 0 comment '状态',
  create_time     datetime default CURRENT_TIMESTAMP null,
  update_time     datetime default CURRENT_TIMESTAMP null,
  del             tinyint default 0 comment '删除 0 false 未删， 1 true 已删'
) comment '文章阅读记录';

drop table if exists announcement;
create table announcement
(
  id            int(11) auto_increment primary key,
  title         varchar(500)                       null comment '标题',
  content       varchar(2000)                      null comment '详细内容',
  keyword       varchar(50)                        null comment '关键词',
  description   varchar(500)                       null comment '描述',
  release_state int(1)  default 0 comment '发布状态 0未发布 1.已发布',
  create_time   datetime default CURRENT_TIMESTAMP null,
  update_time   datetime default CURRENT_TIMESTAMP null,
  del           tinyint default 0 comment '删除 0 false 未删， 1 true 已删'
) comment '公告';

drop table if exists programa;
create table programa
(
  id           int(11) auto_increment primary key,
  column_name  varchar(500)                       null comment '栏目名称',
  column_alias varchar(500)                       null comment '栏目别名',
  parent_id    int(11)                            null comment '父节点',
  keyword      varchar(500)                       null comment '关键词',
  description  varchar(500)                       null comment '描述',
  state        int(1)  default 0 comment '状态',
  create_time  datetime default CURRENT_TIMESTAMP null,
  update_time  datetime default CURRENT_TIMESTAMP null,
  del          tinyint default 0 comment '删除 0 false 未删， 1 true 已删'
) comment '栏目';

drop table if exists links;
create table links
(
  id            int(11) auto_increment primary key,
  uid           int(11)                            not null comment '创建人id',
  link_name     varchar(500)                       null comment '名称',
  link_url      varchar(500)                       null comment '连接url',
  pic_url       varchar(500)                       null comment '图片url',
  description   varchar(500)                       null comment '描述',
  target        int(1)                             not null comment '目标 0._blank新窗口 1._self 2._top',
  rel           int(1)                             not null comment '是否跟踪访问记录0.nofllow 1.none',
  release_state int(1)  default 0 comment '发布状态 0未增加 1.已增加',
  create_time   datetime default CURRENT_TIMESTAMP null,
  update_time   datetime default CURRENT_TIMESTAMP null,
  del           tinyint default 0 comment '删除 0 false 未删， 1 true 已删'
) comment '友情链接';

drop table if exists links_fllow;
create table links_fllow
(
  id          int(11) auto_increment primary key,
  uid         int(11)                            not null comment '用户id',
  links_id    int(11)                            not null comment '链接id',
  create_time datetime default CURRENT_TIMESTAMP null
) comment '友情链接点击跟踪';

drop table if exists system_basic;
create table system_basic
(
  title                  varchar(500) null comment '标题',
  subheading             varchar(500) null comment '副标题',
  record_number          varchar(50)  null comment '备案号',
  login_timeout_settings varchar(500) null comment '登陆超时设置',
  url                    varchar(500) null comment '站点url',
  keyword                varchar(500) null comment '关键词',
  description            varchar(500) null comment '描述',
  email                  varchar(500) null comment '邮箱'
) comment '系统配置';

create table upload_file
(
  id              int(11) unsigned auto_increment comment 'id'
    primary key,
  file_name       varchar(500) default ''  not null comment '文件名称',
  file_size       int                      not null comment '文件大小',
  file_type       int                      not null comment '文件类型',
  uid    varchar(11) default ''   not null comment '上传人',
  file_url        varchar(1024) default '' not null comment '文件存储地址',
  sort            int default '0'          null comment '排序值',
  create_time   datetime default CURRENT_TIMESTAMP null,
  update_time   datetime default CURRENT_TIMESTAMP null,
  del           tinyint default 0 comment '删除 0 false 未删， 1 true 已删'
) comment '上传文件表'