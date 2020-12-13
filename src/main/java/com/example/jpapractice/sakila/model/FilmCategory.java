package com.example.jpapractice.sakila.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "film_category")
@Getter @Setter
public class FilmCategory {
    @EmbeddedId
    private FilmCategoryKey key;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne
    @MapsId("filmId")
    @JoinColumn(name = "film_id")
    @JsonBackReference
    private Film film;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;


}
