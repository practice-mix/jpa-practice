package com.example.jpapractice.sakila.model.projection;

import org.springframework.beans.factory.annotation.Value;

/**
 * A projection interface using @Value is an open projection. Spring Data cannot apply query execution optimizations in this case,
 *
 * The expressions used in @Value should not be too complex
 *
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
public interface ActorFullName2 {

    @Value("#{target.firstName+' '+target.lastName}")
    String getFullName();
}
