package com.example.jpapractice.sakila.model.projection;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Luo Bao Ding
 * @since 12/14/2020
 */
@Getter
@Setter
public class MyFlightDTO {

    private String id;

    private String name;

    private String depaAirportName;

    private String arriAirportName;

    public MyFlightDTO(String id, String name, String depaAirportName, String arriAirportName) {
        this.id = id;
        this.name = name;
        this.depaAirportName = depaAirportName;
        this.arriAirportName = arriAirportName;
    }

    public MyFlightDTO() {
    }


}
