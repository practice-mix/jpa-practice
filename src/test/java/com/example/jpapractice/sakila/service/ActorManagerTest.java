package com.example.jpapractice.sakila.service;

import com.example.jpapractice.sakila.model.Actor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luo Bao Ding
 * @since 12/13/2020
 */
@SpringBootTest
class ActorManagerTest {

    @Autowired
    ActorManager actorManager;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void findActorByFirstName() throws JsonProcessingException {
        List<Actor> result = actorManager.findActorByFirstName("MENA");
        System.out.println(objectMapper.writeValueAsString(result));
    }
}