package com.example.jpapractice.sakila.specification;

import com.example.jpapractice.sakila.model.Film;
import com.example.jpapractice.sakila.model.Film_;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
public class FilmSpecs {
    public static Specification<Film> isLongLength() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get(Film_.length), 120);
        };
    }

    public static Specification<Film> isLongRentalDuration() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get(Film_.rentalDuration), 5);
        };
    }

}
