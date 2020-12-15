package com.example.jpapractice.sakila.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Luo Bao Ding
 * @since 12/15/2020
 */
@MappedSuperclass
@Getter
@Setter
public class AbstractCustomIdGenerate {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "customUuid")
    private String id;

    public AbstractCustomIdGenerate(){}

    public AbstractCustomIdGenerate(String id) {
        this.id = id;
    }
}
