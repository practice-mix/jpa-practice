package com.example.jpapractice.sakila.service;

import com.example.jpapractice.sakila.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Luo Bao Ding
 * @since 12/9/2020
 */
@Service
@RequiredArgsConstructor
public class ActorManager {

    private final ActorRepository actorRepository;



}
