CREATE TABLE `airport_confine`
(
    `id`                         varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
    `fly_past_airport_code_json` json                                   NOT NULL COMMENT '飞过机场三字码json array',
    `apart_day`                  int                                    NOT NULL COMMENT '相隔天数',
    `no_fly_airport_code_json`   json                                   NOT NULL COMMENT '不飞机场三字码json array',
    `status`                     tinyint(1)                             NOT NULL COMMENT '状态',
    `remark`                     varchar(255) COLLATE utf8mb4_general_ci             DEFAULT NULL COMMENT '备注',
    `create_time`                datetime                                            DEFAULT NULL COMMENT '创建时间',
    `update_time`                datetime                                            DEFAULT NULL COMMENT '修改时间',
    `creator`                    char(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
    `modifier`                   char(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最后一次修改人',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='机场限制';
