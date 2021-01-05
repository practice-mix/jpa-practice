create table my_json
(
    id          int auto_increment primary key,
    json_list   json,
    json_obj    json,
    create_time timestamp default current_timestamp,
    update_time timestamp default current_timestamp on update current_timestamp
);
alter table my_json
    add column json_obj2 json after json_obj;

