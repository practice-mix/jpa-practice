package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.Actor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
@Repository
public class ActorCustomRepositoryImpl implements ActorCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    @Override
    public Actor appendSuffixToLastName(Integer id, String suffix) {
        Actor actor = entityManager.find(Actor.class, id);
        actor.setLastName(actor.getLastName() + " " + suffix);
        return actor;
    }

}
