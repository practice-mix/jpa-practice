package com.example.jpapractice.sakila.config;


import com.example.jpapractice.sakila.model.MyFlightNo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Luo Bao Ding
 * @since 12/18/2020
 */
@Converter(autoApply = true)
public class MyFlightNoClassificationConverter implements AttributeConverter<MyFlightNo.Classification, Integer> {
    @Override
    public Integer convertToDatabaseColumn(MyFlightNo.Classification attribute) {
        return attribute.getValue();
    }

    @Override
    public MyFlightNo.Classification convertToEntityAttribute(Integer dbData) {
        return MyFlightNo.Classification.parseFromValue(dbData);
    }

}
