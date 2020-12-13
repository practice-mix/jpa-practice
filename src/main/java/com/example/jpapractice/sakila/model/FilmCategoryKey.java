package com.example.jpapractice.sakila.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Luo Bao Ding
 * @since 12/13/2020
 */
@Embeddable
@Data
public class FilmCategoryKey implements Serializable {
    private Integer filmId;

    private Integer categoryId;
}
