package com.example.jpapractice.sakila.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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


    private transient final @Transient
    List<Object> domainEvents = new ArrayList<>();  //alternative: org.springframework.data.domain.AbstractAggregateRoot

    public <T> MyFlight registerEvent(T event) {
        Assert.notNull(event, "Domain event must not be null!");
        this.domainEvents.add(event);
        return this;
    }

    @DomainEvents
    private Collection<Object> domainEvents() { //events returned by this method are published using the ApplicationEventPublisher interface
        return Collections.unmodifiableList(domainEvents);
    }

    @AfterDomainEventPublication
    private void clearDomainEvents() {
        this.domainEvents.clear();
    }


}
