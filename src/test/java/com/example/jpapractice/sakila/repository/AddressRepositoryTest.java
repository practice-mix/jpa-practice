package com.example.jpapractice.sakila.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

/**
 * @author Luo Bao Ding
 * @since 12/18/2020
 */
@SpringBootTest
class AddressRepositoryTest {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void takeAddressSummary() throws JsonProcessingException {
        var result = addressRepository.takeAddressSummary(PageRequest.of(0, 5));
//        System.out.println(result.getContent().get(0).getLocation());
        System.out.println(objectMapper.writeValueAsString(result));

    }
}