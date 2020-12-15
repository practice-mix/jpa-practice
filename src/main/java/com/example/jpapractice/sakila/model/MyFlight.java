package com.example.jpapractice.sakila.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "my_flight")
@Getter
@Setter
public class MyFlight {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid2")//generator always override id
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "depa_airport_id")
    @JsonBackReference
    private MyAirport depaAirport;

    @ManyToOne
    @JoinColumn(name = "arri_airport_id")
    @JsonBackReference
    private MyAirport arriAirport;


}
