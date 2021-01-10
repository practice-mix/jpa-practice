package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.Film;
import com.example.jpapractice.sakila.model.projection.FilmExcerpt;
import com.example.jpapractice.sakila.model.projection.FilmExcerptValue;
import com.example.jpapractice.sakila.model.projection.FilmSummary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
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
public interface FilmRepository extends JpaRepository<Film, Integer>, JpaSpecificationExecutor<Film>, QueryByExampleExecutor<Film>, FilmCustomRepository {

    //HQL
    @EntityGraph(value = "film-graph",type = EntityGraph.EntityGraphType.FETCH)
    @Query(value = "select  f from Film  f join f.filmActors fa where fa.key.actorId=:actorId")
    List<Film> findByActorId(@Param("actorId") Integer actorId);

    //JPQL: join
    @Query(value = "select f.filmId as filmId,f.title as title,f.releaseYear as releaseYear ,fa.key.actorId as actorId from Film  f join f.filmActors fa where fa.key.actorId=:actorId")
    List<FilmExcerpt> takeExcerptByActorId(@Param("actorId") Integer actorId);

    //JPQL: join
    @Query(value = "select new com.example.jpapractice.sakila.model.projection.FilmExcerptValue(f.filmId,f.title ,f.releaseYear,fa.key.actorId )   from Film  f join f.filmActors fa where fa.key.actorId=:actorId")
    List<FilmExcerptValue> takeExcerptValueByActorId(@Param("actorId") Integer actorId);

//   fail
//    @Query(value = "select f.filmId as filmId,f.title as title,f.releaseYear as releaseYear ,fa.key.actorId as actorId from Film  f join f.filmActors fa where fa.key.actorId=:actorId")//HQL
//    List<FilmExcerptDto> takeFilmExcerptDtoByActorId(@Param("actorId") Integer actorId);

    @Query(value = "select f.title as title,f.rating as rating,f.description as description,f.releaseYear as releaseYear,a.firstName as firstName , a.lastName as lastName,c.name as categoryName  from Film f join f.filmActors fa join fa.actor a join f.filmCategory fc join fc.category c where ftsTwo( a.firstName,a.lastName,:searchText)>0 and fc.key.categoryId=:categoryId")
    List<FilmSummary> takeFilmSummary(@Param("searchText") String searchText, @Param("categoryId") Integer categoryId, Pageable pageable);

    @Query(value = "select f.title as title,f.rating as rating,f.description as description,f.releaseYear as releaseYear,a.firstName as firstName , a.lastName as lastName,c.name as categoryName  from Film f join f.filmActors fa join fa.actor a join f.filmCategory fc join fc.category c where match( a.firstName,a.lastName,:searchText)>0 and fc.key.categoryId=:categoryId")
    List<FilmSummary> takeFilmSummaryCustomRegisterFunctions(@Param("searchText") String searchText, @Param("categoryId") Integer categoryId, Pageable pageable);

}
