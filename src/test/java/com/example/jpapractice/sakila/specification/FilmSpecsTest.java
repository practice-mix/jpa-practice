package com.example.jpapractice.sakila.specification;

import com.example.jpapractice.sakila.model.Film;
import com.example.jpapractice.sakila.repository.FilmRepository;
import com.example.jpapractice.sakila.request.ActorFilmRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static com.example.jpapractice.sakila.specification.FilmSpecs.*;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
@SpringBootTest
class FilmSpecsTest {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void isLongLength() throws JsonProcessingException {
        Page<Film> result = filmRepository.findAll(FilmSpecs.isLongLength(), PageRequest.of(0, 10));
        System.out.println(objectMapper.writeValueAsString(result));

    }

    @Test
    void combineSpec() throws JsonProcessingException {
        Page<Film> result = filmRepository.findAll(FilmSpecs.isLongRentalDuration().and(FilmSpecs.isLongLength()), PageRequest.of(0, 10));
        System.out.println(objectMapper.writeValueAsString(result));
    }

    /* select one table from multiple tables
     */
    @Test
    void findFilmByActor() throws JsonProcessingException {
        ActorFilmRequest request = new ActorFilmRequest();
//        request.setActorId(1);
//        request.setFirstName("MENA");
        request.setLastName("HOPPER");
        Page<Film> result = filmRepository.findAll(request.toSpec(), PageRequest.of(0, 10));
        System.out.println(objectMapper.writeValueAsString(result));

    }
}