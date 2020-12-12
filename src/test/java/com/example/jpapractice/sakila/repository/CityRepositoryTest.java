package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.projection.CitySummary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
@SpringBootTest
class CityRepositoryTest {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void findByCityId() throws JsonProcessingException {
        CitySummary result = cityRepository.findByCityId(1);
        System.out.println(objectMapper.writeValueAsString(result));
    }


}