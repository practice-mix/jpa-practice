package com.example.jpapractice.sakila.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
/**
 * @author Luo Bao Ding
 * @since 12/13/2020
 */
@SpringBootTest
@AutoConfigureMockMvc
class ActorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void showActor() throws Exception {
        String result = mockMvc.perform(get("/actor/1")).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
     @Test
    void reuseProjection() throws Exception {
        String result = mockMvc.perform(get("/actor/reuseProjection?firstName=PENELOPE")).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
     @Test
    void querydsl() throws Exception {
        String result = mockMvc.perform(get("/actor/querydsl?firstName=PENELOPE&size=5&page=0&sort=actorId")).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


}