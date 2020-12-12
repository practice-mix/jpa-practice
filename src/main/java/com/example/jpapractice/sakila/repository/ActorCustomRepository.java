package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.Actor;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
public interface ActorCustomRepository {
    Actor appendSuffixToLastName(Integer id, String suffix);

}
