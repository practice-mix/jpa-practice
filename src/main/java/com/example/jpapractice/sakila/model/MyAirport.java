package com.example.jpapractice.sakila.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "my_airport")
@Getter
@Setter
@NoArgsConstructor
public class MyAirport {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid2") //generator always override id
    private String id;

    @Column(name = "name")
    private String name;

    public MyAirport(String id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "depaAirport")
    @JsonManagedReference
    private List<MyFlight> depaFlights;

    @OneToMany(mappedBy = "arriAirport")
    @JsonManagedReference
    private List<MyFlight> arriFlights;

}
