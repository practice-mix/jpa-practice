package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Luo Bao Ding
 * @since 12/13/2020
 */
@Component
@RequiredArgsConstructor
public class FilmCustomRepositoryImpl  implements FilmCustomRepository{

    private final EntityManager entityManager;
    @Override
    public List<Film> listByActorFirstName(String firstName) {
        Query query = entityManager.createNativeQuery("select f.* from film f join film_actor fa on f.film_id = fa.film_id join actor a on a.actor_id = fa.actor_id where a.first_name like '%" + firstName + "%'",
                Film.class);
        return query.getResultList();
    }

}
