create table my_airport
(
    id                  varchar(36)                        not null
        primary key,
    name                varchar(20)                        null,
    size                int                                null,
    classification      int                                null,
    list                json                               null,
    update_time         datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    create_time         datetime default CURRENT_TIMESTAMP null,
    app_gen_update_time datetime                           null,
    app_gen_create_time datetime                           null,
    created_by          varchar(20)                        null,
    modified_by         varchar(20)                        null
);


