-- auto-generated definition
create table my_json
(
    id          int auto_increment
        primary key,
    json_list   json                                null,
    json_obj    json                                null,
    json_obj2   json                                null,
    create_time timestamp default CURRENT_TIMESTAMP null,
    update_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

