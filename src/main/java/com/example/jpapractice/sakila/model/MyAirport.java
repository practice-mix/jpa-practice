package com.example.jpapractice.sakila.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "my_airport")
@Getter
@Setter
public class MyAirport extends AbstractCustomIdGenerate {

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Integer size;

    @Column(name = "create_time", updatable = false)
    private LocalDateTime createTime;

    public MyAirport() {
    }

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
