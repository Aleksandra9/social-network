alter table user_info add constraint user_info_pk primary key (id);

create table if not exists friend
(
    id varchar constraint friend_pk primary key,
    user_id varchar
        constraint friend_user_info_user_id_fk
        references user_info,
    friend_id varchar
        constraint friend_user_info_friend_id_fk
        references user_info,
    create_datetime timestamp with time zone default now(),
    delete_datetime timestamp with time zone
);

create index if not exists friend_user_id_index
    on network.friend (user_id);

create index if not exists friend_friend_id_index
    on network.friend (friend_id);