package com.example.jpapractice.sakila.service;

import com.example.jpapractice.sakila.model.Actor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Luo Bao Ding
 * @since 12/13/2020
 */
@SpringBootTest
class ActorServiceTest {

    @Autowired
    ActorService actorService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void findActorByFirstName() throws JsonProcessingException {
        List<Actor> result = actorService.findActorByFirstName("MENA");
        System.out.println(objectMapper.writeValueAsString(result));
    }

    @Test
    void findActorByLastName() throws JsonProcessingException {
        List<Actor> result = actorService.findActorByLastName("GUINESS");
        System.out.println(objectMapper.writeValueAsString(result));
    }


}