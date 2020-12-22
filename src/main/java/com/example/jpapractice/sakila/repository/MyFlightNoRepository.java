package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.MyFlightNo;
import com.example.jpapractice.sakila.model.request.MyFlightNoSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Luo Bao Ding
 * @since 12/21/2020
 */
public interface MyFlightNoRepository extends JpaRepository<MyFlightNo, Integer> {
    //can not dynamic
    @Query(value = "select f from MyFlightNo f where " +
            "  f.no = :#{#request.no}  " +
            "and f.classification = :#{#request.classification}  " +
//            "and f.unusedSchedule = :#{#request.unusedSchedule}  " +
//            "and f.usedSchedule = :#{#request.usedSchedule}  " +
            "and ftsOne(f.requestedSchedule,:#{#request.requestedScheduleStr})>0  " +
            "")
    Page<MyFlightNo> searchStatic(@Param("request") MyFlightNoSearch request, Pageable pageable);
}
