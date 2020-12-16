package com.example.jpapractice.sakila.engine;

import com.example.jpapractice.sakila.model.event.MyFlightCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author Luo Bao Ding
 * @since 12/16/2020
 */
@Service
public class Engine {


    @TransactionalEventListener
    public void handleMyFlightCreatedEvent(MyFlightCreatedEvent event) {
        System.out.println("handle event: MyFlightCreatedEvent " + event);

    }
}
