package com.example.jpapractice.sakila.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author Luo Bao Ding
 * @since 12/9/2020
 */
@Entity
@Getter
@Setter
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="actor_id")
    private Integer actorId;


//    @ManyToMany(mappedBy = "actors")
//    private List<Film> films;

    @OneToMany(mappedBy = "actor",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<FilmActor> filmActors;


    private String firstName;

    private String lastName;


    @LastModifiedDate
    private LocalDateTime lastUpdate;


}
