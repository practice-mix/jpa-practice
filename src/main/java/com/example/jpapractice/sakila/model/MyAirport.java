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
public class MyAirport extends AbstractCustomIdGenerate {

    @Column(name = "name")
    private String name;

    public MyAirport(){}

    public MyAirport(String id) {
       super(id);
    }

//    //remove  N+1
//    @OneToMany(mappedBy = "depaAirport")
//    @JsonManagedReference
//    private List<MyFlight> depaFlights;
//
//    @OneToMany(mappedBy = "arriAirport")
//    @JsonManagedReference
//    private List<MyFlight> arriFlights;

}
