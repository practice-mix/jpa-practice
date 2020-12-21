package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.MyFlightNo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Luo Bao Ding
 * @since 12/21/2020
 */
public interface MyFlightNoRepository extends JpaRepository<MyFlightNo, Integer> {

}
