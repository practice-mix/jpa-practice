package com.example.jpapractice.sakila.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.locationtech.jts.geom.Point;

/**
 * @author Luo Bao Ding
 * @since 12/18/2020
 */
public interface AddressSummary {
    String getAddress();

    String getDistrict();

    String getCity();

    @JsonIgnore
    Point getLocation();

    default String getLocationWkt() {
        return getLocation().toString();
    }

}
