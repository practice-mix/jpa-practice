create table my_flight
(
    id              varchar(36)                        not null
        primary key,
    name            varchar(20)                        null,
    duration        int                                null,
    password        varchar(20)                        null,
    depa_airport_id varchar(36)                        null,
    arri_airport_id varchar(36)                        null,
    update_time     datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

