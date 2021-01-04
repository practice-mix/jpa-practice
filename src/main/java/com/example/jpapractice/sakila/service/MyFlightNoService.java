package com.example.jpapractice.sakila.service;

import com.example.jpapractice.sakila.model.MyFlightNo;
import com.example.jpapractice.sakila.model.MyFlightNo_;
import com.example.jpapractice.sakila.model.request.MyFlightNoSearch;
import com.example.jpapractice.sakila.repository.MyFlightNoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Luo Bao Ding
 * @since 12/21/2020
 */
@Service
@RequiredArgsConstructor
public class MyFlightNoService {

    private final MyFlightNoRepository repository;

    private final EntityManager entityManager;

    Page<MyFlightNo> searchByNativeSql(MyFlightNoSearch request, Pageable pageable) {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereCause = new ArrayList<String>();

        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append("select * from my_flight_no  ");

        if (request.getNo() != null) {
            whereCause.add(" no =:no ");
            paramaterMap.put("no", request.getNo());
        }
        if (request.getClassification() != null) {
            whereCause.add(" classification =:classification ");
            paramaterMap.put("classification", request.getClassification().getValue());
        }

        tableBuilder.append(" where ").append(StringUtils.join(whereCause, " and "));

        tableBuilder.append(" limit ").append(" :offset,:pageSize");

        Query tableQuery = entityManager.createNativeQuery(tableBuilder.toString(), MyFlightNo.class);
        for (String key : paramaterMap.keySet()) {
            tableQuery.setParameter(key, paramaterMap.get(key));
        }
        tableQuery.setParameter("offset", pageable.getOffset());
        tableQuery.setParameter("pageSize", pageable.getPageSize());

        List<MyFlightNo> resultList = tableQuery.getResultList();

        return new PageImpl<>(resultList, pageable, countByNativeSql(paramaterMap, whereCause));
    }

    private Long countByNativeSql(Map<String, Object> paramaterMap, List<String> whereCause) {
        StringBuilder countBuilder = new StringBuilder();
        countBuilder.append("select count(*) from my_flight_no ").append(" where ").append(StringUtils.join(whereCause, " and "));

        Query countQuery = entityManager.createNativeQuery(countBuilder.toString());
        for (String key : paramaterMap.keySet()) {
            countQuery.setParameter(key, paramaterMap.get(key));
        }
        return ((BigInteger) countQuery.getSingleResult()).longValue();
    }

    Page<MyFlightNo> searchByCriteria(MyFlightNoSearch request, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<MyFlightNo> tableQuery = builder.createQuery(MyFlightNo.class);
        Root<MyFlightNo> entity = tableQuery.from(MyFlightNo.class);

        tableQuery.select(entity);
        tableQuery.where(getPredicates(request, builder, entity));

        TypedQuery<MyFlightNo> typedQuery = entityManager.createQuery(tableQuery);
        setParameters(request, typedQuery);

        List<MyFlightNo> resultList = typedQuery
                .setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();

        return new PageImpl<>(resultList, pageable, countByCriteria(request));

    }

    private long countByCriteria(MyFlightNoSearch request) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<MyFlightNo> root = countQuery.from(MyFlightNo.class);
        countQuery.select(builder.count(root));
        countQuery.where(getPredicates(request, builder, root));

        TypedQuery<Long> typedQuery = entityManager.createQuery(countQuery);
        setParameters(request, typedQuery);

        return typedQuery.getSingleResult();

    }

    private void setParameters(MyFlightNoSearch request, TypedQuery typedQuery) {
        if (request.getRequestedScheduleStr() != null) {
            typedQuery.unwrap(org.hibernate.query.Query.class).setParameter("terms", request.getRequestedScheduleStr());
        }
    }

    private Predicate getPredicates(MyFlightNoSearch request, CriteriaBuilder builder, Root<MyFlightNo> entity) {
        List<Predicate> predicates = new ArrayList<>();
        if (request.getNo() != null) {
            predicates.add(builder.equal(entity.get(MyFlightNo_.no), request.getNo()));
        }
        if (request.getClassification() != null) {
            predicates.add(builder.equal(entity.get(MyFlightNo_.classification), request.getClassification()));
        }
        if (request.getRequestedScheduleStr() != null) {
            ParameterExpression<String> terms = builder.parameter(
                    String.class,
                    "terms"
            );

            predicates.add(builder.gt(
                    builder.function("ftsOne", Double.class, entity.get(MyFlightNo_.requestedSchedule), terms),
                    0
            ));
        }

        return builder.and(predicates.toArray(new Predicate[]{}));

    }
}
