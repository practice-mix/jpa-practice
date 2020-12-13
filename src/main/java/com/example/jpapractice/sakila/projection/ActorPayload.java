package com.example.jpapractice.sakila.projection;

import org.springframework.data.web.JsonPath;
import org.springframework.data.web.ProjectedPayload;

/**
 * @author Luo Bao Ding
 * @since 12/13/2020
 */
@ProjectedPayload
public interface ActorPayload {

    @JsonPath("$.firstName")
    String getFirstName();

    @JsonPath({ "$.lastName" })
    String getLastName();
}
