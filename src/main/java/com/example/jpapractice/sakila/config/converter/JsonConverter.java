package com.example.jpapractice.sakila.config.converter;

import com.example.jpapractice.sakila.model.MyJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;

//@Converter(autoApply = true)//avoid conflict with @TypeDef(name="json",typeClass = JsonStringType.class)
public class JsonConverter implements AttributeConverter<MyJson.JsonObj, String> {//cannot use Object

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(MyJson.JsonObj meta) {
        try {
            return objectMapper.writeValueAsString(meta);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public MyJson.JsonObj convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, MyJson.JsonObj.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
