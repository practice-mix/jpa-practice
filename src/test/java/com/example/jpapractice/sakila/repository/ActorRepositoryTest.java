package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.Actor;
import com.example.jpapractice.sakila.projection.NamesOnly;
import com.example.jpapractice.sakila.projection.NamesOnly2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
//@DataJpaTest
@SpringBootTest
public class ActorRepositoryTest {
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EntityManager entityManager;

    @Test
    void findByLastNameContainsOrderByLastUpdate() throws JsonProcessingException {
        List<Actor> actors = actorRepository.findByLastNameContainsOrderByLastUpdate("SS");
        System.out.println(objectMapper.writeValueAsString(actors));

    }

    @Test
    void findByLastNameStartingWith() throws JsonProcessingException {
        List<Actor> actors = actorRepository.findByLastNameStartingWith("GUI");
        System.out.println(objectMapper.writeValueAsString(actors));

    }

    @Test
    void takeByFirstName() throws JsonProcessingException {
        List<Actor> actors = actorRepository.takeByFirstName("NICK");
        System.out.println(objectMapper.writeValueAsString(actors));

    }

    @Test
    void takeByLastName() throws JsonProcessingException {
        List<Actor> actors = actorRepository.takeByLastName("WAHLBERG");
        System.out.println(objectMapper.writeValueAsString(actors));

    }

    @Test
    void takeByLastNameEndsWith() throws JsonProcessingException {
        List<Actor> actors = actorRepository.takeByLastNameEndsWith("BERG");
        System.out.println(objectMapper.writeValueAsString(actors));

    }

    @Test
    void takeByLastNameNative() throws JsonProcessingException {
        List<Actor> actors = actorRepository.takeByLastNameNative("WAHLBERG");
        System.out.println(objectMapper.writeValueAsString(actors));
    }

    @Test
    void pageByLastName() throws JsonProcessingException {
        Page<Actor> actors = actorRepository.pageByLastName("WAHLBERG", PageRequest.of(0, 1));
        System.out.println(objectMapper.writeValueAsString(actors));
    }

    @Test
    void listByLastNameSort() throws JsonProcessingException {
        List<Actor> actors = actorRepository.listByLastNameSort("WAHLBERG", Sort.by("lastName"));
        System.out.println(objectMapper.writeValueAsString(actors));
    }

    @Test
    void listByLastNameSortLenUnsafe() throws JsonProcessingException {
        List<Object[]> actors = actorRepository.listByLastNameSortLenUnsafe("WAHLBERG", JpaSort.unsafe("length(lastName)"));
        System.out.println(objectMapper.writeValueAsString(actors));
    }

    @Test
    void listByFirstNameOrLastName() throws JsonProcessingException {
        List<Actor> actors = actorRepository.listByFirstNameOrLastName("WAHLBERG", "WAHLBERG");
        System.out.println(objectMapper.writeValueAsString(actors));
    }

    @Test
    void sort() throws JsonProcessingException {
        List<Actor> actors = actorRepository.sort(Sort.by("firstName"));
        System.out.println(objectMapper.writeValueAsString(actors));
    }

    //    @Test
//    void takeByLastNameSeg() throws JsonProcessingException {
//        List<Actor> actors = actorRepository.takeByLastNameSeg("BE");
//        System.out.println(objectMapper.writeValueAsString(actors));
//    }
    @Test
    void updateFirstNameById() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(actorRepository.findById(201)));
        Integer result = actorRepository.updateFirstNameById(201, "bao ding 2");
        System.out.println(objectMapper.writeValueAsString(result));

        System.out.println(objectMapper.writeValueAsString(actorRepository.findById(201)));
    }


    @Test
    void jpaWayUpdate() throws JsonProcessingException {//first query, then update, then save
        actorRepository.findById(201).ifPresent(actor -> {
            actor.setFirstName("bao ding 3");
            actorRepository.save(actor);
        });

    }

    @Test
    void appendSuffixToLastName() throws JsonProcessingException {
        Actor result = actorRepository.appendSuffixToLastName(201, "postfix");
        System.out.println(result);
    }

    @Test
    void deleteByLastNameEquals() {
        Integer result = actorRepository.deleteByLastNameEquals("aa");
        System.out.println(result);

    }

    @Test
    void delByLastName() {
        Integer result = actorRepository.delByLastName("aa");
        System.out.println(result);

    }

    @Test
    void findByLastNameEquals() throws JsonProcessingException {
        var result = actorRepository.findByLastNameEquals("cc");
        System.out.println(objectMapper.writeValueAsString(result));
    }

    @Test
    void findByFirstNameEquals() throws JsonProcessingException {
        var result = actorRepository.findByFirstNameEquals("cc", NamesOnly.class);
        System.out.println(objectMapper.writeValueAsString(result));

        var result2 = actorRepository.findByFirstNameEquals("cc", NamesOnly2.class);
        System.out.println(objectMapper.writeValueAsString(result2));


    }


}