create table my_flight_no
(
    id                 int auto_increment
        primary key,
    no                 varchar(20)                        not null comment '航班号',
    requested_schedule varchar(255)                       null comment '申请班期，逗号分隔',
    used_schedule      varchar(255)                       null comment '申请班期，逗号分隔',
    unused_schedule    varchar(255)                       null comment '未用班期，逗号分隔',
    classification     int                                null comment '类别，如保时刻',
    ftx                varchar(255)                       null,
    update_time        datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'
);

create fulltext index ftx
    on my_flight_no (ftx);

create fulltext index requested_schedule
    on my_flight_no (requested_schedule);

create fulltext index unused_schedule
    on my_flight_no (unused_schedule);

create fulltext index used_schedule
    on my_flight_no (used_schedule);

