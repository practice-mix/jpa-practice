package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Luo Bao Ding
 * @since 12/9/2020
 */
@RepositoryRestResource
public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
