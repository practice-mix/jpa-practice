package com.example.jpapractice.sakila.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "my_flight_no")
@Getter
@Setter
public class MyFlightNo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "no")
    private String no;

    //    @Column(name = "requested_schedule")
//    @Type(type = "EnumSet", parameters = {
//            @org.hibernate.annotations.Parameter(name = "enumClass", value = "com.example.jpapractice.sakila.model.MyFlightNo$ScheduleUnit")
//    })
    private String requestedSchedule;

    @Column(name = "used_schedule")
    @Type(type = "EnumList", parameters = {
            @org.hibernate.annotations.Parameter(name = "enumClass", value = "com.example.jpapractice.sakila.model.MyFlightNo$ScheduleUnit")
    })
    @Convert(disableConversion = true)
    private List<ScheduleUnit> usedSchedule;//todo: query

    @Column(name = "unused_schedule")
    @Convert(converter = MyFlightNoScheduleListConverter.class)
//avoid conflict form json converter if any, otherwise need not to add this  @Convert
    private List<ScheduleUnit> unusedSchedule;//MyFlightNoScheduleListConverter

    @Column(name = "classification")
    @Convert(converter = MyFlightNoClassificationConverter.class)
    private Classification classification;

    @Column(name = "update_time", insertable = false, updatable = false)
    private LocalDateTime updateTime;

    public enum Classification {
        PROTECT_HOUR(1), PROTECT_CABOTAGE(2), MILITARY(3), OTHERS(4);

        private final int value;

        Classification(int value) {
            this.value = value;
        }

        @JsonValue
        public int getValue() {
            return value;
        }

        public static Classification parseFromValue(int value) {
            for (Classification enu : Classification.values()) {
                if (enu.getValue() == value) {
                    return enu;
                }
            }
            return null;
        }
    }

    @Converter(autoApply = true)
    public static class MyFlightNoClassificationConverter implements AttributeConverter<MyFlightNo.Classification, Integer> {
        @Override
        public Integer convertToDatabaseColumn(MyFlightNo.Classification attribute) {
            return attribute.getValue();
        }

        @Override
        public MyFlightNo.Classification convertToEntityAttribute(Integer dbData) {
            return MyFlightNo.Classification.parseFromValue(dbData);
        }

    }

    public enum ScheduleUnit {
        MON, TWU, WED, THU, FRI, SAT, SUN;

        @JsonValue
        public int getValue() {
            return ordinal();
        }

    }

    @Converter(autoApply = true)
    public static class MyFlightNoScheduleListConverter implements AttributeConverter<List<MyFlightNo.ScheduleUnit>, String> {
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


}
