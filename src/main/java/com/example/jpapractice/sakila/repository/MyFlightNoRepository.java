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

    @Query(value = "select * from my_flight_no f where 1=1 " +
            " and if(:#{#request.no} is not null,f.no = :#{#request.no},1=1)   " +
            "and if(:#{#request.classification} is not null,f.classification=:#{#request.classification.value} ,1=1)  " +
            "and if(:#{#request.unusedScheduleStr} is not null,match(f.unused_schedule)  against(:#{#request.unusedScheduleStr } in boolean  mode),1=1)  " +
            "and if(:#{#request.usedScheduleStr} is not null, match( f.used_schedule ) against(:#{#request.usedScheduleStr} in boolean  mode) ,1=1)  " +
            "and if(:#{#request.requestedScheduleStr} is not null, match(f.requested_schedule) against (:#{#request.requestedScheduleStr} in boolean  mode) ,1=1) " +
            "", nativeQuery = true)
    Page<MyFlightNo> searchDynamic(@Param("request") MyFlightNoSearch request, Pageable pageable);


}
