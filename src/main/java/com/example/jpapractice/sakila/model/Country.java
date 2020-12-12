package com.example.jpapractice.sakila.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "country")
@Getter
@Setter
public class Country {
    @Id
    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country")
    private String country;

    @Column(name = "last_update")
    @LastModifiedDate
    private LocalDateTime lastUpdate;

}
