package com.example.jpapractice.sakila.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Luo Bao Ding
 * @since 12/11/2020
 */
@Embeddable
@Data
public class FilmActorKey implements Serializable {

//    @Column(name = "actor_id")
    private Integer actorId;


//    @Column(name = "film_id")
    private Integer filmId;

}
