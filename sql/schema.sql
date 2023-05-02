drop table if exists member CASCADE;
create table member
(
    id        bigint generated by default as identity,
    user_id varchar,
    user_password varchar,
    name     integer,
    gender   varchar,
    phone    varchar,
    address varchar,
    email     varchar,
    birth       varchar,
    primary key (id)
);