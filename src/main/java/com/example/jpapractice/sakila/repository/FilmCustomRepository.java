package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.Film;

import java.util.List;

/**
 * @author Luo Bao Ding
 * @since 12/13/2020
 */
public interface FilmCustomRepository {

    List<Film> listByActorFirstName(String firstName);

}
