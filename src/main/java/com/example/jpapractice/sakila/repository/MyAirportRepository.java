package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.MyAirport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Luo Bao Ding
 * @since 12/14/2020
 */
public interface MyAirportRepository extends JpaRepository<MyAirport,String> {


}
