package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Transactional
    Integer deleteByCountryEquals(String name);
}
