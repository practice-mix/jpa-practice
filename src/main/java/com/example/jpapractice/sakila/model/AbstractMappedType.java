package com.example.jpapractice.sakila.model;

import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
@MappedSuperclass
public class AbstractMappedType {

    @LastModifiedDate
    private LocalDateTime lastUpdate;
}
