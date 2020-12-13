package com.example.jpapractice.sakila.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
@MappedSuperclass
@Getter
@Setter
public class AbstractMappedType {

    @LastModifiedDate
    private LocalDateTime lastUpdate;
}
