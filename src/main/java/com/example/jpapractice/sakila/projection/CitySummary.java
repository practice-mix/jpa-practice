package com.example.jpapractice.sakila.projection;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
public interface CitySummary {

    String getCity();

    CountrySummary getCountry();

    interface CountrySummary{
        String getCountry();
    }
}
