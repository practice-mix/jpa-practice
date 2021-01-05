package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.MyJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyJsonRepositoryTest {
    @Autowired
    MyJsonRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void selectById() throws JsonProcessingException {
        MyJson entity = repository.findById(1).orElse(null);
        System.out.println(objectMapper.writeValueAsString(entity));

    }

}
