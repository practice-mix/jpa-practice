package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.City;
import com.example.jpapractice.sakila.projection.CitySummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
public interface CityRepository extends JpaRepository<City,Integer> {

    CitySummary findByCityId(Integer id);

}
