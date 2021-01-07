package com.example.jpapractice.sakila.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "my_json")
@Getter
@Setter
public class MyJson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Type(type = "json")
    @Column(name = "json_list")
    private List<Object> jsonList;

    @Type(type = "json")
    @Column(name = "json_obj")
    private JsonObj jsonObj;

    @Convert(converter = JsonObjConverter.class)
    @Column(name = "json_obj2")
    private JsonObj jsonObj2;

    @Column(name = "create_time", updatable = false, insertable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time", updatable = false, insertable = false)
    private LocalDateTime updateTime;

    @Column(name = "used_schedules")
    @Type(type = "json")
    private List<ScheduleUnit> usedSchedules;


    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JsonObj {
        private String username;

        private Integer age;
    }

    //@Converter(autoApply = true)//avoid conflict with @TypeDef(name="json",typeClass = JsonStringType.class)
    public static class JsonObjConverter implements AttributeConverter<MyJson.JsonObj, String> {//cannot use Object

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

    public enum ScheduleUnit {
        MON, TWU, WED, THU, FRI, SAT, SUN;

        @JsonValue
        public int getValue() {
            return ordinal();
        }

    }
}

