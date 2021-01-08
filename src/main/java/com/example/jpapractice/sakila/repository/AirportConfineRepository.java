package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.AirportConfine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author Luo Bao Ding
 * @since 1/8/2021
 */
public interface AirportConfineRepository extends JpaRepository<AirportConfine, String>, QuerydslPredicateExecutor<AirportConfine> {
}
