package com.example.jpapractice.sakila.service;

import com.example.jpapractice.sakila.model.MyFlightNo;
import com.example.jpapractice.sakila.model.request.MyFlightNoSearch;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;

/**
 * @author Luo Bao Ding
 * @since 12/21/2020
 */
@SpringBootTest
class MyFlightNoServiceTest {

    @Autowired
    MyFlightNoService service;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void searchByCriteria() throws JsonProcessingException {
        MyFlightNoSearch request = new MyFlightNoSearch();
        request.setNo("a6");
        request.setClassification(MyFlightNo.Classification.PROTECT_CABOTAGE);
        request.setRequestedSchedule(Arrays.asList(MyFlightNo.ScheduleUnit.WED, MyFlightNo.ScheduleUnit.SUN));
        Page<MyFlightNo> result = service.searchByCriteria(request, PageRequest.of(0, 2));
        System.out.println(objectMapper.writeValueAsString(result));

    }

    @Test
    void searchByNativeSql() throws JsonProcessingException {
        MyFlightNoSearch request = new MyFlightNoSearch();
        request.setNo("a6");
        request.setClassification(MyFlightNo.Classification.PROTECT_CABOTAGE);
        Page<MyFlightNo> result = service.searchByNativeSql(request, PageRequest.of(0, 2));
        System.out.println(objectMapper.writeValueAsString(result));

    }


}