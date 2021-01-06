package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.MyJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class MyJsonRepositoryTest {
    @Autowired
    MyJsonRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void selectById() throws JsonProcessingException {
        MyJson entity = repository.findById(1).orElse(null);
        System.out.println(objectMapper.writeValueAsString(entity));

    }

    @Test
    void save() throws JsonProcessingException {
        MyJson entity = new MyJson();
        entity.setJsonList(Arrays.asList("a", "b", "d"));
        entity.setJsonObj(MyJson.JsonObj.builder().age(17).username("love").build());
        entity.setJsonObj2(MyJson.JsonObj.builder().age(17).username("you").build());
        entity.setUsedSchedules(Arrays.asList(MyJson.ScheduleUnit.FRI, MyJson.ScheduleUnit.MON, MyJson.ScheduleUnit.SAT));
        MyJson result = repository.save(entity);
        System.out.println(objectMapper.writeValueAsString(result));

    }


}
