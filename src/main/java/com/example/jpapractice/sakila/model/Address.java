package com.example.jpapractice.sakila.model;

import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {
    @Id
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "address")
    private String address;

    @Column(name = "address2")
    private String address2;

    @Column(name = "district")
    private String district;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "location")
    private Point location;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
