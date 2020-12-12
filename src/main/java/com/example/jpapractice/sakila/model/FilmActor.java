package com.example.jpapractice.sakila.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private FilmActorKey key;

    @ManyToOne
    @JoinColumn(name="actor_id")
    @MapsId("actorId")
//    @JsonBackReference
    private Actor actor;

    @ManyToOne
    @JoinColumn(name="film_id")
    @MapsId("filmId")
//    @JsonBackReference
    private Film film;

    /**
     * last_update
     */
    private Date lastUpdate;

}