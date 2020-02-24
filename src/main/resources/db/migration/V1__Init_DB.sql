create table hibernate_sequence (next_val bigint);

insert into hibernate_sequence values ( 1 );

insert into hibernate_sequence values ( 1 );

create table message (id bigint not null auto_increment, filename varchar(255), tag varchar(255), text varchar(255), user_id bigint, primary key (id));

create table role_group (id bigint not null, role_group_name varchar(255), primary key (id));

create table role_group_roles (role_group_id bigint not null, roles varchar(255));

create table user_role (user_id bigint not null, roles varchar(255));

create table user_role_group (user_id bigint not null, role_groups varchar(255));

create table usr (id bigint not null, activation_code varchar(255), active bit not null, email varchar(255), password varchar(255) not null, username varchar(255) not null, primary key (id));

alter table message add constraint FK70bv6o4exfe3fbrho7nuotopf foreign key (user_id) references usr (id);

alter table role_group_roles add constraint FKlynexiqck74g4t1tawgp6bty3 foreign key (role_group_id) references role_group (id);

alter table user_role add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references usr (id);

alter table user_role_group add constraint FK4ymmhx62mx29e7athjgc4qyjd foreign key (user_id) references usr (id);