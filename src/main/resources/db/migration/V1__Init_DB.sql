create table category (
  id      bigserial not null,
  created timestamp not null,
  updated timestamp not null,
  title   varchar(25),
  primary key (id)
);

create table post (
  id          bigserial not null,
  created     timestamp not null,
  updated     timestamp not null,
  description varchar(255),
  text        TEXT,
  title       varchar(255),
  category_id int8      not null,
  usr_id      int8,
  primary key (id)
);

create table user_role (
  usr_id int8 not null,
  roles  varchar(255)
);

create table usr (
  id              bigserial not null,
  created         timestamp not null,
  updated         timestamp not null,
  activation_code varchar(255),
  email           varchar(255),
  password        varchar(255),
  username        varchar(20),
  primary key (id)
);

alter table post
  add constraint fk_post_category_id foreign key (category_id) references category;

alter table post
  add constraint fk_post_usr_id foreign key (usr_id) references usr;

alter table user_role
  add constraint fk_user_role_usr_id foreign key (usr_id) references usr;

