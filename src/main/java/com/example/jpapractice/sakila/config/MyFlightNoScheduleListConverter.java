package com.example.jpapractice.sakila.config;

import com.example.jpapractice.sakila.model.MyFlightNo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Luo Bao Ding
 * @since 12/21/2020
 */
@Converter(autoApply = true)
public class MyFlightNoScheduleListConverter implements AttributeConverter<List<MyFlightNo.ScheduleUnit>, String> {
    private static final String SEPARATOR = ",";
    private final MyFlightNo.ScheduleUnit[] enumConstants = MyFlightNo.ScheduleUnit.class.getEnumConstants();

    @Override
    public String convertToDatabaseColumn(List<MyFlightNo.ScheduleUnit> attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.stream().map(v -> Integer.toString(v.ordinal())).collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public List<MyFlightNo.ScheduleUnit> convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Arrays.stream(dbData.split(SEPARATOR)).map(Integer::parseInt).map(ordinal -> enumConstants[ordinal]).collect(Collectors.toList());

    }
}
