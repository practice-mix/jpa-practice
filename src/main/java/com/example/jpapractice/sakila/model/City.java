package com.example.jpapractice.sakila.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "city")
public class City {
    @Id
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city")
    private String city;

//    @Column(name = "country_id")
//    private Integer countryId;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
