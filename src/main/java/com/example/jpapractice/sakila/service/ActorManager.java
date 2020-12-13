package com.example.jpapractice.sakila.service;

import com.example.jpapractice.sakila.model.Actor;
import com.example.jpapractice.sakila.model.QActor;
import com.example.jpapractice.sakila.repository.ActorRepository;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Luo Bao Ding
 * @since 12/9/2020
 */
@Service
@RequiredArgsConstructor
public class ActorManager {

    private final ActorRepository actorRepository;

    private final EntityManager entityManager;


    /**
     * querydsl
     */
    public List<Actor> findActorByFirstName(String firstName) {
        QActor actor = QActor.actor;
        JPAQuery<Actor> query = new JPAQuery<>(entityManager);
        return query.select(actor).from(actor).where(actor.firstName.eq(firstName)).fetch();
    }


}
