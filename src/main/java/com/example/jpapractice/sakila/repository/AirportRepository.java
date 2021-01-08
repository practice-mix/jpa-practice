package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Luo Bao Ding
 * @since 1/8/2021
 */
public interface AirportRepository extends JpaRepository<Airport, String> {
}
