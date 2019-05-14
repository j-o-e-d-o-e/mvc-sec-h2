-- schema to create tables in persist-db
drop table if exists user_product;
drop table if exists user_role;
drop table if exists address;
drop table if exists product;
drop table if exists role;
drop table if exists user;

create table address
(
    id       bigint not null auto_increment,
    city     varchar(255),
    street   varchar(255),
    zip_code varchar(5),
    user_id  bigint,
    primary key (id)
) engine = InnoDB;
create table product
(
    id          bigint           not null auto_increment,
    color       varchar(255),
    description varchar(255),
    icon        varchar(255),
    name        varchar(255),
    price       double precision not null,
    primary key (id)
) engine = InnoDB;
create table role
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
) engine = InnoDB;
create table user
(
    id         bigint not null auto_increment,
    first_name varchar(255),
    last_name  varchar(255),
    password   varchar(255),
    username   varchar(255),
    primary key (id)
) engine = InnoDB;
create table user_product
(
    user_id    bigint not null,
    product_id bigint not null
) engine = InnoDB;
create table user_role
(
    user_id bigint not null,
    role_id bigint not null
) engine = InnoDB;

alter table address
    add constraint FKda8tuywtf0gb6sedwk7la1pgi foreign key (user_id) references user (id);
alter table user_product
    add constraint FKnw43wab2rt35jmofmpbhkibco foreign key (product_id) references product (id);
alter table user_product
    add constraint FKq5o2e33vlwpfc2k1mredtia6p foreign key (user_id) references user (id);
alter table user_role
    add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role (id);
alter table user_role
    add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user (id);