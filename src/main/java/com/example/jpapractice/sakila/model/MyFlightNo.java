package com.example.jpapractice.sakila.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
//    @Type(type = "enumSet", parameters = {
//            @org.hibernate.annotations.Parameter(name = "enumClass", value = "com.example.jpapractice.sakila.model.MyFlightNo$ScheduleUnit")
//    })
private String requestedSchedule;

    @Column(name = "used_schedule")
    @Type(type = "EnumList", parameters = {
            @org.hibernate.annotations.Parameter(name = "enumClass", value = "com.example.jpapractice.sakila.model.MyFlightNo$ScheduleUnit")
    })
    @Convert(disableConversion = true)
    private List<ScheduleUnit> usedSchedule;//todo: query

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Column(name = "unused_schedule")
    private List<ScheduleUnit> unusedSchedule;//MyFlightNoScheduleListConverter

    @Column(name = "classification")
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

    public enum ScheduleUnit {
        MON, TWU, WED, THU, FRI, SAT, SUN;

        @JsonValue
        public int getValue() {
            return ordinal();
        }

    }


}
