package com.example.jpapractice.sakila.projection;

/**
 * @author Luo Bao Ding
 * @since 12/14/2020
 */
public interface MyFlightView {

    String getId();

    String getName();

    AirportName getDepaAirport();

    AirportName getArriAirport();

    interface AirportName {
        String getName();
    }
}
