package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.MyAirport;
import com.example.jpapractice.sakila.model.MyFlight;
import com.example.jpapractice.sakila.model.event.MyFlightCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

/**
 * @author Luo Bao Ding
 * @since 12/14/2020
 */
@SpringBootTest
public class MyFlightRepositoryTest {

    @Autowired
    MyFlightRepository flightRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void createFlight() throws JsonProcessingException {
        MyFlight flight = new MyFlight();
        flight.setName("flight 4");
        flight.setDepaAirport(new MyAirport("2"));
        flight.setArriAirport(new MyAirport("3"));

        MyFlight result = flightRepository.save(flight.registerEvent(new MyFlightCreatedEvent(flight.getName())));
        System.out.println(objectMapper.writeValueAsString(result));

    }

    @Test
    void listFlight() throws JsonProcessingException {
        var result = flightRepository.findAll(PageRequest.of(0, 10));
        System.out.println(objectMapper.writeValueAsString(result));
    }

    @Test
    void viewFlight() throws JsonProcessingException {
        var result = flightRepository.findBy(PageRequest.of(0, 10));
        System.out.println(objectMapper.writeValueAsString(result));
    }

    @Test
    void checkOutFlightDto() throws JsonProcessingException {
        var result = flightRepository.checkOutFlightDto(PageRequest.of(0, 10));
        System.out.println(objectMapper.writeValueAsString(result));

    }
}
