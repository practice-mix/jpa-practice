package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.MyJson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyJsonRepository extends JpaRepository<MyJson, Integer> {
}
