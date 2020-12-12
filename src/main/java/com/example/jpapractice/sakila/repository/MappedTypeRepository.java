package com.example.jpapractice.sakila.repository;

import com.example.jpapractice.sakila.model.AbstractMappedType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author Luo Bao Ding
 * @since 12/12/2020
 */
@NoRepositoryBean
public interface MappedTypeRepository<T extends AbstractMappedType,ID> extends JpaRepository<T, ID> {

    @Query(value = "select  e from #{#entityName} e")
    List<T> sort(Sort sort);
}
