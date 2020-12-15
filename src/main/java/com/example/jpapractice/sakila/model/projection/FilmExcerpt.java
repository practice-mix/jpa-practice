package com.example.jpapractice.sakila.model.projection;

/**
 * from multiple table
 *
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
public interface FilmExcerpt {
    Integer getFilmId();
    Integer getActorId();
    String getTitle();
    String getReleaseYear();
}
