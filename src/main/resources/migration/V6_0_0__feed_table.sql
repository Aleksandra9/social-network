create table if not exists feed
(
    id varchar primary key,
    user_id varchar
        constraint feed_user_id_fk
        references user_info,
    post_id varchar
        constraint feed_post_id_fk
        references post,
    create_datetime timestamp with time zone default now()
);

create index if not exists feed_user_id_index
    on feed (user_id);

create index if not exists feed_post_id_index
    on feed (post_id);