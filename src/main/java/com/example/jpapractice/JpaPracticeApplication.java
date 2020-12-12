package com.example.jpapractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableTransactionManagement
public class JpaPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaPracticeApplication.class, args);
    }

}
