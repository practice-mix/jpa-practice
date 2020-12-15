package com.example.jpapractice.sakila.model.projection;

import lombok.Value;

/**
 * from multiple table
 *
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
@Value
public class FilmExcerptValue {
    Integer filmId;
    String title;
    String releaseYear;
    Integer actorId;
}
