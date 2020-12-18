package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.Address;
import com.example.jpapractice.sakila.model.projection.AddressSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Luo Bao Ding
 * @since 12/18/2020
 */
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("select a.address as address ,a.district as district,c.city as city from Address a join City c on a.cityId=c.cityId ")
    Page<AddressSummary> takeAddressSummary(Pageable pageable);

}
