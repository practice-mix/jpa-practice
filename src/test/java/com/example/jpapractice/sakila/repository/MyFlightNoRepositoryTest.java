package com.example.jpapractice.sakila.repository;

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
//        entity.setRequestedSchedule(EnumSet.copyOf(Arrays.asList(MyFlightNo.ScheduleUnit.WED, MyFlightNo.ScheduleUnit.SAT)));
        MyFlightNo result = repository.save(entity);
        System.out.println(objectMapper.writeValueAsString(result));

    }

    @Test
    void list() throws JsonProcessingException {
        List<MyFlightNo> result = repository.findAll();
        System.out.println(objectMapper.writeValueAsString(result));

    }

    @Test
    void searchStatic() throws JsonProcessingException {
        MyFlightNoSearch request = new MyFlightNoSearch();
        request.setNo("a6");
        request.setClassification(MyFlightNo.Classification.PROTECT_CABOTAGE);
//       request.setUnusedSchedule(Arrays.asList(MyFlightNo.ScheduleUnit.WED, MyFlightNo.ScheduleUnit.SUN));//fail: attribute convert
//       request.setUsedSchedule(Arrays.asList(MyFlightNo.ScheduleUnit.WED, MyFlightNo.ScheduleUnit.SUN));//fail: custom type descriptor
        request.setRequestedSchedule(Arrays.asList(MyFlightNo.ScheduleUnit.WED, MyFlightNo.ScheduleUnit.SUN));
        Page<MyFlightNo> result = repository.searchStatic(request, PageRequest.of(0, 2));
        System.out.println(objectMapper.writeValueAsString(result));

    }

    @Test
    void searchDynamic() throws JsonProcessingException {
        MyFlightNoSearch request = new MyFlightNoSearch();
        request.setNo("a6");
        request.setClassification(MyFlightNo.Classification.PROTECT_CABOTAGE);
//       request.setUnusedSchedule(Arrays.asList(MyFlightNo.ScheduleUnit.WED, MyFlightNo.ScheduleUnit.SUN));
//       request.setUsedSchedule(Arrays.asList(MyFlightNo.ScheduleUnit.WED, MyFlightNo.ScheduleUnit.SUN));
        request.setRequestedSchedule(Arrays.asList(MyFlightNo.ScheduleUnit.WED, MyFlightNo.ScheduleUnit.SUN));
        Page<MyFlightNo> result = repository.searchDynamic(request, PageRequest.of(0, 2));
        System.out.println(objectMapper.writeValueAsString(result));

    }

}