create table if not exists post
(
    id varchar primary key,
    author_user_id varchar
        constraint post_user_info_author_user_id_fk
        references user_info,
    text varchar,
    create_datetime timestamp with time zone default now(),
    update_datetime timestamp with time zone,
    delete_datetime timestamp with time zone
);