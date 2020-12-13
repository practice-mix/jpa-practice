package com.example.jpapractice.sakila.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NamedEntityGraphs(
        value = {
                @NamedEntityGraph(
                        name = "film-graph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "filmCategory",subgraph = "category-graph")
                        },
                        subgraphs = {
                                @NamedSubgraph(
                                        name = "category-graph",
                                        attributeNodes = {
                                                @NamedAttributeNode(value = "category")
                                        }
                                )
                        }
                )
        }
)
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
//    @JsonManagedReference
    private List<FilmActor> filmActors;


    @OneToOne(mappedBy = "film")
    @JsonManagedReference
    private FilmCategory filmCategory;

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
    private Boolean languageId;

    /**
     * original_language_id
     */
    private Boolean originalLanguageId;

    /**
     * rental_duration
     */
    private Integer rentalDuration;

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
    private LocalDateTime lastUpdate;


}
