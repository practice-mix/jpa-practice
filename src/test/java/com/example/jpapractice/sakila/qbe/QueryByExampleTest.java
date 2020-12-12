package com.example.jpapractice.sakila.qbe;

import com.example.jpapractice.sakila.model.Film;
import com.example.jpapractice.sakila.repository.FilmRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
@SpringBootTest
public class QueryByExampleTest {
    @Autowired
    FilmRepository filmRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void simple() throws JsonProcessingException {
        Film film = new Film();
        film.setRating("NC-17");
        Example<Film> example = Example.of(film);
        Page<Film> result = filmRepository.findAll(example, PageRequest.of(0, 5));
        System.out.println(objectMapper.writeValueAsString(result));
    }

    /**
     * The property specifier accepts property names (such as firstname and lastname).
     *
     * You can navigate by chaining properties together with dots (address.city). You can also tune it with matching options and case sensitivity.
     */
    @Test
    void custom() throws JsonProcessingException {
        Film film = new Film();
        film.setTitle("S");
        film.setRentalDuration(5);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.ENDING)
                .withMatcher("rentalDuration", ExampleMatcher.GenericPropertyMatcher::exact)
                ;


        Example<Film> example = Example.of(film, exampleMatcher);

        Page<Film> result = filmRepository.findAll(example, PageRequest.of(0, 5));
        System.out.println(objectMapper.writeValueAsString(result));
    }
}
