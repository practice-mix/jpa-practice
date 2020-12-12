package com.example.jpapractice.sakila.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
@SpringBootTest
class CountryRepositoryTest {

    @Autowired
    CountryRepository countryRepository;

    @Test
    void deleteByCountryEquals() {
        Integer result = countryRepository.deleteByCountryEquals("aa");
        System.out.println(result);
    }
}