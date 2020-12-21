package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.MyFlightNo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/**
 * @author Luo Bao Ding
 * @since 12/21/2020
 */
@SpringBootTest
class MyFlightNoRepositoryTest {
    @Autowired
    MyFlightNoRepository repository;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void saveWithEnumAttributeConverter() throws JsonProcessingException {
        MyFlightNo entity = new MyFlightNo();
        entity.setNo("a1");
        entity.setClassification(MyFlightNo.Classification.MILITARY);
        MyFlightNo result = repository.save(entity);
        System.out.println(objectMapper.writeValueAsString(result));

    }

    @Test
    void saveWithEnumListAttributeConverter() throws JsonProcessingException {
        MyFlightNo entity = new MyFlightNo();
        entity.setNo("a6");
        entity.setClassification(MyFlightNo.Classification.MILITARY);
        entity.setUnusedSchedule(Arrays.asList(MyFlightNo.ScheduleUnit.MON, MyFlightNo.ScheduleUnit.FRI));
        entity.setUsedSchedule(Arrays.asList(MyFlightNo.ScheduleUnit.TWU, MyFlightNo.ScheduleUnit.THU));
        entity.setRequestedSchedule(EnumSet.copyOf(Arrays.asList(MyFlightNo.ScheduleUnit.WED, MyFlightNo.ScheduleUnit.SAT)));
        MyFlightNo result = repository.save(entity);
        System.out.println(objectMapper.writeValueAsString(result));

    }

    @Test
    void list() throws JsonProcessingException {
        List<MyFlightNo> result = repository.findAll();
        System.out.println(objectMapper.writeValueAsString(result));

    }

}