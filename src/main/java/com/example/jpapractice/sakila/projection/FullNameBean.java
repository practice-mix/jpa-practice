package com.example.jpapractice.sakila.projection;

import com.example.jpapractice.sakila.model.Actor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
