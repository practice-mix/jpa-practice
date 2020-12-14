package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.MyFlight;
import com.example.jpapractice.sakila.projection.MyFlightDTO;
import com.example.jpapractice.sakila.projection.MyFlightView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Luo Bao Ding
 * @since 12/14/2020
 */
public interface MyFlightRepository  extends JpaRepository<MyFlight,String> {


    Page<MyFlightView> findBy(Pageable pageable);


    @Query(value = "select  new com.example.jpapractice.sakila.projection.MyFlightDTO(f.id,f.name,da.name,aa.name)" +
            " from MyFlight f left join f.depaAirport da  left join  f.arriAirport aa ")
    Page<MyFlightDTO> checkOutFlightDto(Pageable pageable);

}
