package com.example.jpapractice.sakila.model.request;

import com.example.jpapractice.sakila.model.Actor;
import com.example.jpapractice.sakila.model.Film;
import com.example.jpapractice.sakila.model.FilmActor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luo Bao Ding
 * @since 12/13/2020
 */
@Data
public class ActorFilmRequest {
    private Integer actorId;
    private String firstName;
    private String lastName;


    public  Specification<Film> toSpec() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();

            if (actorId != null) {
                Join<FilmActor,Film> join = root.join("filmActors", JoinType.LEFT);
                Predicate actorIdEqual = criteriaBuilder.equal(join.get("key").get("actorId"), actorId);
                list.add(actorIdEqual);
            }
            if (firstName != null) {
                Join<FilmActor, Film> join1 = root.join("filmActors", JoinType.LEFT);
                Join<Actor, FilmActor> join2 = join1.join("actor", JoinType.LEFT);
                Predicate firstNameLike = criteriaBuilder.like(join2.get("firstName"), "%" + firstName + "%");
                list.add(firstNameLike);
            }
            if (lastName != null) {
                Join<FilmActor, Film> join1 = root.join("filmActors", JoinType.LEFT);
                Join<Actor, FilmActor> join2 = join1.join("actor", JoinType.LEFT);
                Predicate lastNameLike = criteriaBuilder.like(join2.get("lastName"), "%" + lastName + "%");
                list.add(lastNameLike);
            }

            return criteriaBuilder.and(list.toArray(new Predicate[0]));

        };
    }
}
