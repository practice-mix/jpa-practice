package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.Film;
import com.example.jpapractice.sakila.projection.FilmExcerptValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
@SpringBootTest
class FilmRepositoryTest {
    @Autowired
    FilmRepository filmRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void findByActorId() throws JsonProcessingException {
        List<Film> result = filmRepository.findByActorId(1);
        System.out.println(objectMapper.writeValueAsString(result));

    }

    @Test
    void takeExcerptByActorId() throws JsonProcessingException {
        var result = filmRepository.takeExcerptByActorId(1);
        System.out.println(objectMapper.writeValueAsString(result));

    }

    @Test
    void takeExcerptValueByActorId() throws JsonProcessingException {
        var result = filmRepository.takeExcerptValueByActorId(1);
        System.out.println(objectMapper.writeValueAsString(result));
    }

}