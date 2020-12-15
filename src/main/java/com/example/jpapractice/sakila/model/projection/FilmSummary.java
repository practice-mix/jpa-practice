package com.example.jpapractice.sakila.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Luo Bao Ding
 * @since 12/13/2020
 */
public interface FilmSummary {

    String getTitle();

    String getRating();

    String getDescription();

    String getReleaseYear();

    @JsonIgnore
    String getFirstName();

    @JsonIgnore
    String getLastName();

    default String getFullName() {
        if (getFirstName() != null) {
            return getFirstName().concat(" ").concat(getLastName());
        } else {
            return getLastName();
        }
    }

    String getCategoryName();


}
