package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.MyAirport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Luo Bao Ding
 * @since 12/14/2020
 */
@SpringBootTest
public class MyAirportRepositoryTest {
    @Autowired
    MyAirportRepository airportRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createAirportManualId() throws JsonProcessingException {
        MyAirport airport = new MyAirport();
        airport.setId("3");
        airport.setName("name1");

        MyAirport result = airportRepository.save(airport);
        System.out.println(objectMapper.writeValueAsString(result));

    }
    
    @Test
    void createAirportGenId() throws JsonProcessingException {
        MyAirport airport = new MyAirport();
        airport.setName("name2");

        MyAirport result = airportRepository.save(airport);
        System.out.println(objectMapper.writeValueAsString(result));

    }


}
