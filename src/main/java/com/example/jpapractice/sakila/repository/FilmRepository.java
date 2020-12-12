package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.Film;
import com.example.jpapractice.sakila.projection.FilmExcerpt;
import com.example.jpapractice.sakila.projection.FilmExcerptValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

/**
 * @author Luo Bao Ding
 * @since 12/10/2020
 */
public interface FilmRepository extends JpaRepository<Film,Integer>, JpaSpecificationExecutor<Film>, QueryByExampleExecutor<Film> {

//HQL
    @Query(value = "select  f from Film  f join f.filmActors fa where fa.key.actorId=:actorId")
    List<Film> findByActorId(@Param("actorId") Integer actorId);

    //JPQL
    @Query(value = "select f.filmId as filmId,f.title as title,f.releaseYear as releaseYear ,fa.key.actorId as actorId from Film  f join f.filmActors fa where fa.key.actorId=:actorId")//HQL
    List<FilmExcerpt> takeExcerptByActorId(@Param("actorId") Integer actorId);

    //JPQL
    @Query(value = "select new com.example.jpapractice.sakila.projection.FilmExcerptValue(f.filmId,f.title ,f.releaseYear,fa.key.actorId )   from Film  f join f.filmActors fa where fa.key.actorId=:actorId")//HQL
    List<FilmExcerptValue> takeExcerptValueByActorId(@Param("actorId") Integer actorId);

}
