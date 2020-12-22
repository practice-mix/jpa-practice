package com.example.jpapractice.sakila.model.request;

import com.example.jpapractice.sakila.model.MyFlightNo;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Luo Bao Ding
 * @since 12/21/2020
 */
@Data
public class MyFlightNoSearch {
    private String no;

    private MyFlightNo.Classification classification;

    private List<MyFlightNo.ScheduleUnit> unusedSchedule;

    private List<MyFlightNo.ScheduleUnit> usedSchedule;

    private List<MyFlightNo.ScheduleUnit> requestedSchedule;


//    private transient String requestedScheduleStr;

    public String getRequestedScheduleStr() {
        if (requestedSchedule == null) {
            return null;
        }
        return requestedSchedule.stream().map(MyFlightNo.ScheduleUnit::getValue).map(o -> Integer.toString(o)).map(t -> " +" + t).collect(Collectors.joining(" "));
    }

    public String getUnusedScheduleStr() {
        if (unusedSchedule == null) {
            return null;
        }
        return unusedSchedule.stream().map(MyFlightNo.ScheduleUnit::getValue).map(o -> Integer.toString(o)).map(t -> " +" + t).collect(Collectors.joining(" "));
    }

    public String getUsedScheduleStr() {
        if (usedSchedule == null) {
            return null;
        }
        return usedSchedule.stream().map(MyFlightNo.ScheduleUnit::getValue).map(o -> Integer.toString(o)).map(t -> " +" + t).collect(Collectors.joining(" "));
    }


}
