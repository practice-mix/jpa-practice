package com.example.jpapractice.sakila.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "film_actor")
@Getter
@Setter
public class FilmActor{


    @EmbeddedId
    private FilmActorKey id;

    @ManyToOne
    @MapsId("actorId")
    @JoinColumn(name="actor_id")
    private Actor actor;

    @ManyToOne
    @MapsId("filmId")
    @JoinColumn(name="film_id")
    private Film film;

    /**
     * last_update
     */
    private Date lastUpdate;

}