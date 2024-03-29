drop table if exists reply CASCADE;
create table reply
(
    id        bigint generated by default as identity,
    user_id integer,
    user_name varchar,
    comment_id integer,
    content varchar,
    created_time timestamp,
    like_count integer,

    FOREIGN KEY (comment_id) REFERENCES comment (id),
    FOREIGN KEY (user_id) REFERENCES member (id),

    primary key (id)
);
