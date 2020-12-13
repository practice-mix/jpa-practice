package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.Actor;
import com.example.jpapractice.sakila.projection.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @author Luo Bao Ding
 * @since 12/9/2020
 */
public interface ActorRepository extends MappedTypeRepository<Actor, Integer>, ActorCustomRepository, QuerydslPredicateExecutor<Actor>{

    List<Actor> findByLastNameContainsOrderByLastUpdate(String lastName);

    List<Actor> findByLastNameStartingWith(String lastnamePrefix);

    @Query("select a from Actor a where a.firstName=?1")
    List<Actor> takeByFirstName(String firstName);

    List<Actor> takeByLastName(String lastName);

    @Query("select a from Actor a where a.lastName like %?1")
    List<Actor> takeByLastNameEndsWith(String lastNameSuffix);

    @Query(value = "select a.actor_id, a.first_name,a.last_name,a.last_update from actor a where a.last_name = ?1", nativeQuery = true)
    List<Actor> takeByLastNameNative(String lastName);

    @Query(value = "select actor_id, first_name, last_name, last_update from  actor where last_name=?1",
            countQuery = "select  count(*) from actor where last_name=?1",
            nativeQuery = true)
    Page<Actor> pageByLastName(String lastName, Pageable pageable);


    @Query(value = "select  a from Actor a where a.lastName=?#{[0]}")
    List<Actor> listByLastNameSort(String lastName, Sort sort);

    @Query(value = "select  a.actorId, LENGTH(a.lastName) as fn_len from Actor a where a.lastName=?1")
    List<Object[]> listByLastNameSortLenUnsafe(String lastName, Sort sort);


    @Query(value = "select  a from #{#entityName} a where a.firstName=:firstName or a.lastName=:lastName")
    List<Actor> listByFirstNameOrLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

//    @Query("select a from Actor a where a.lastName like %#{escape([0])}% escape ?#{escapeCharacter()}")// @EnableJpaRepositories.escapeCharacter
//    List<Actor> takeByLastNameSeg(String lastNameSeg);

    @Transactional
    @Modifying
    @Query(value = "update Actor a set a.firstName=:newFirstName where a.actorId=:id")
//jpa can not automatically recognize transactional DML statement from @Query
    Integer updateFirstNameById(@Param("id") Integer id, @Param("newFirstName") String newFirstName);

    @Transactional
    Integer deleteByLastNameEquals(String lastName);//query-delete pattern


    @Transactional
    @Modifying
    @Query(value = "delete from Actor a  where  a.lastName=:lastName")
//one delete sql without entity lifecycle callback
    Integer delByLastName(@Param("lastName") String lastName);


    Collection<NamesOnly2> findByLastNameEquals(String lastName);

    <T> Collection<T> findByFirstNameEquals(String firstName, Class<T> type);


    List<Actor> findByFirstNameContainsOrLastNameContainsOrderByLastNameDesc(String firstName, String lastName);


    @Query(value = "select  a.* from actor a where match(first_name,last_name) against(:searchText )", nativeQuery = true)
    Page<Actor> fullTextSearchName(@Param("searchText") String searchText, Pageable pageable);
}
