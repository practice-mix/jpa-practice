package com.example.jpapractice.sakila.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "my_airport")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class MyAirport extends AbstractCustomIdGenerate {

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Integer size;

    @Column(name = "create_time", updatable = false, insertable = false)//db maintain
    private LocalDateTime createTime;

    @Column(name = "update_time", updatable = false, insertable = false)//db maintain
    private LocalDateTime updateTime;

    @Column(name = "app_gen_update_time")
    @LastModifiedDate
    private LocalDateTime appGenUpdateTime;

    @Column(name = "app_gen_create_time")
    @CreatedDate
    private LocalDateTime app_gen_create_time;

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

    @Column(name = "classification")
    @Enumerated // this annotation can be eliminated
    private Classification classification;

    public MyAirport() {
    }

    public MyAirport(String id) {
        super(id);
    }

//    //remove  N+1
//    @OneToMany(mappedBy = "depaAirport")
//    @JsonManagedReference
//    private List<MyFlight> depaFlights;
//
//    @OneToMany(mappedBy = "arriAirport")
//    @JsonManagedReference
//    private List<MyFlight> arriFlights;


    public enum Classification {
        BIG, MEDIUM, SMALL;
    }
}
