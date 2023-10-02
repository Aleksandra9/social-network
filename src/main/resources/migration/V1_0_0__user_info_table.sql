create table if not exists user_info
(
    id varchar not null,
    first_name varchar,
    second_name varchar,
    age int,
    birthdate date,
    biography varchar,
    city varchar,
    password varchar
);