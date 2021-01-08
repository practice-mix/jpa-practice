CREATE TABLE `airport`
(
    `three_code`             varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NOT NULL COMMENT '机场三字码(目前其他引用都是三字码)',
    `four_code`              varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NOT NULL COMMENT '机场四字码',
    `country_id`             int                                                           NOT NULL COMMENT '国家表id',
    `city_name`              varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '城市',
    `airport_name`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机场名称',
    `airport_en_name`        varchar(100) COLLATE utf8mb4_general_ci                       DEFAULT NULL COMMENT '机场英文名称',
    `airport_short_name`     varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '机场简称，如浦',
    `d_or_i`                 char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NOT NULL COMMENT '国际I,国内D,地区R',
    `dict_airport_nature_id` int                                                           DEFAULT NULL COMMENT '机场性质表id',
    `time_difference`        int                                                           DEFAULT NULL COMMENT '时差，单位分钟',
    `create_time`            datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `update_time`            datetime                                                      DEFAULT NULL COMMENT '修改时间',
    `creator`                char(32) CHARACTER SET utf8 COLLATE utf8_general_ci           DEFAULT NULL COMMENT '创建人',
    `modifier`               char(32) CHARACTER SET utf8 COLLATE utf8_general_ci           DEFAULT NULL COMMENT '最后一次修改人',
    PRIMARY KEY (`three_code`) USING BTREE,
    UNIQUE KEY `uk_four_code` (`four_code`) USING BTREE COMMENT '四字码唯一'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='机场';
