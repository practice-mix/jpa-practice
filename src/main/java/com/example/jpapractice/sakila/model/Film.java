package com.example.jpapractice.sakila.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity

public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Integer filmId;

//    @ManyToMany
//    @JoinTable(name = "film_actor",
//            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
//            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id")
//    )
//    private List<Actor> actors;

    @OneToMany(mappedBy = "film",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<FilmActor> filmActors;

    /**
     * Q
     * title
     */
    private String title;

    /**
     * description
     */
    private String description;

    /**
     * release_year
     */
    private String releaseYear;

    /**
     * language_id
     */
    private boolean languageId;

    /**
     * original_language_id
     */
    private boolean originalLanguageId;

    /**
     * rental_duration
     */
    private boolean rentalDuration;

    /**
     * rental_rate
     */
    private BigDecimal rentalRate;

    /**
     * length
     */
    private Integer length;

    /**
     * replacement_cost
     */
    private BigDecimal replacementCost;

    /**
     * rating
     */
    private String rating;

    /**
     * aries ， deleted scenes ， behind the scenes )
     */
    private String specialFeatures;

    /**
     * last_update
     */
    private Date lastUpdate;


}
