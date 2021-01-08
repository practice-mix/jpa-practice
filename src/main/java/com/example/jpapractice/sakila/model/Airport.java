package com.example.jpapractice.sakila.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "airport")
@Getter
@Setter
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    @Column(name = "three_code")
    private String threeCode;

    @Column(name = "four_code")
    private String fourCode;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "airport_name")
    private String airportName;

    @Column(name = "airport_en_name")
    private String airportEnName;

    @Column(name = "airport_short_name")
    private String airportShortName;

    @Column(name = "d_or_i")
    private Character dOrI;

    @Column(name = "dict_airport_nature_id")
    private Integer dictAirportNatureId;

    @Column(name = "time_difference")
    private Integer timeDifference;

    @Column(name = "create_time")
    private java.time.LocalDateTime createTime;

    @Column(name = "update_time")
    private java.time.LocalDateTime updateTime;

    @Column(name = "creator")
    private String creator;

    @Column(name = "modifier")
    private String modifier;

}
