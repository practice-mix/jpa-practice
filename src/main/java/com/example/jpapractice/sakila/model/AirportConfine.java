package com.example.jpapractice.sakila.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;


@Entity
@Table(name = "airport_confine")
@Getter
@Setter
public class AirportConfine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(name = "id")
    private String id;

    @Column(name = "fly_past_airport_code_json")
    private String flyPastAirportCodeJson;

    @Column(name = "apart_day")
    private Integer apartDay;

    @Column(name = "no_fly_airport_code_json")
    private String noFlyAirportCodeJson;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "remark")
    private String remark;

    @Column(name = "create_time")
    private java.time.LocalDateTime createTime;

    @Column(name = "update_time")
    private java.time.LocalDateTime updateTime;

    @Column(name = "creator")
    private String creator;

    @Column(name = "modifier")
    private String modifier;


}
