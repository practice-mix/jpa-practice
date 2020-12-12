package com.example.jpapractice.sakila.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Luo Bao Ding
 * @since 12/9/2020
 */
@Entity
@Getter
@Setter
@NamedQuery(name = "Actor.takeByLastName",query = "select a from Actor a where a.lastName=?1")
public class Actor extends  AbstractMappedType{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="actor_id")
    private Integer actorId;


//    @ManyToMany(mappedBy = "actors")
//    private List<Film> films;

    @OneToMany(mappedBy = "actor",fetch = FetchType.LAZY)
    @JsonIgnore
//    @JsonManagedReference
    private List<FilmActor> filmActors;

    private String firstName;

    private String lastName;




}
