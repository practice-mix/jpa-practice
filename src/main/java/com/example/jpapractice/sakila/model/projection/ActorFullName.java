package com.example.jpapractice.sakila.model.projection;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
public interface ActorFullName {
    String getFirstName();

    String getLastName();

    default String getFullName() {
        return getFirstName().concat(" ").concat(getLastName());
    }

}
