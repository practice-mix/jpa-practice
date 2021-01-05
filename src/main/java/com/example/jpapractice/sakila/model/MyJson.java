package com.example.jpapractice.sakila.model;

import com.example.jpapractice.sakila.config.converter.JsonConverter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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

    @Convert(converter = JsonConverter.class)
    @Column(name = "json_obj2")
    private JsonObj jsonObj2;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;


    @Data
    public static class JsonObj {
        private String username;

        private String age;
    }
}

