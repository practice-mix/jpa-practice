package com.example.jpapractice.sakila.engine;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Luo Bao Ding
 * @since 12/16/2020
 */
@Getter
@Setter
public class EventProcessingException extends RuntimeException {

    private String message;

    public EventProcessingException() {
    }

    public EventProcessingException(String message) {
        super(message);
        this.message = message;
    }
}
