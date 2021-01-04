package com.example.jpapractice.sakila.service;

import com.example.jpapractice.sakila.model.Actor;
import com.example.jpapractice.sakila.model.QActor;
import com.example.jpapractice.sakila.repository.ActorRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Luo Bao Ding
 * @since 12/9/2020
 */
@Service
public class ActorService {

    private final ActorRepository actorRepository;

    private final EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    public ActorService(ActorRepository actorRepository, EntityManager entityManager) {
        this.actorRepository = actorRepository;
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * querydsl
     */
    public List<Actor> findActorByFirstName(String firstName) {
        QActor actor = QActor.actor;
        JPAQuery<Actor> query = new JPAQuery<>(entityManager);
        return query.select(actor).from(actor).where(actor.firstName.eq(firstName)).fetch();
    }

    public List<Actor> findActorByLastName(String lastName) {
        QActor actor = QActor.actor;
        return queryFactory.selectFrom(actor).where(actor.lastName.eq(lastName)).fetch();
    }


}
