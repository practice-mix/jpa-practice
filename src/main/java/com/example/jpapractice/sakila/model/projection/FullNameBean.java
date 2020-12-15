package com.example.jpapractice.sakila.model.projection;

import com.example.jpapractice.sakila.model.Actor;
import org.springframework.stereotype.Component;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
@Component("fullNameBean")
public class FullNameBean {

    public String getFullName(Actor actor) {
        return actor.getFirstName().concat(" ").concat(actor.getLastName());
    }
}
