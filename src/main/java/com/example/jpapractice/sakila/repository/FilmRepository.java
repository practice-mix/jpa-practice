package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Luo Bao Ding
 * @since 12/10/2020
 */
@RepositoryRestResource
public interface FilmRepository extends JpaRepository<Film,Integer> {

}
