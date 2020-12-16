package com.example.jpapractice.sakila.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Luo Bao Ding
 * @since 12/16/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyFlightCreatedEvent {
    private String name;

}
