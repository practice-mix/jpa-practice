package com.example.jpapractice.sakila.web;

import com.example.jpapractice.sakila.model.AirportConfine;
import com.example.jpapractice.sakila.model.QAirport;
import com.example.jpapractice.sakila.model.QAirportConfine;
import com.example.jpapractice.sakila.repository.AirportConfineRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Data;
import org.hibernate.transform.ResultTransformer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Luo Bao Ding
 * @since 1/8/2021
 */
@RestController
@RequestMapping("airportConfine")
public class AirportConfineController {

    private final AirportConfineRepository repository;

    private final EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    public AirportConfineController(AirportConfineRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
        queryFactory = new JPAQueryFactory(entityManager);
    }


    @PostMapping
    public AirportConfine create(AirportConfine request) {
        return repository.save(request);
    }

    @GetMapping("/querydsl")
    public Page<AirportConfineVO> querydsl(AirportConfineSearch request, Pageable pageable) {
        QAirportConfine ac = QAirportConfine.airportConfine;
        QAirport a = QAirport.airport;
        QAirport a2 = new QAirport("airport_2");
        BooleanBuilder builder = new BooleanBuilder();
        if (request.getName() != null) {
            builder.and(
                    a.threeCode.like("%" + request.getName() + "%")
                            .or(a.fourCode.like("%" + request.getName() + "%"))
                            .or(a.airportName.like("%" + request.getName() + "%"))
                            .or(a.airportShortName.like("%" + request.getName() + "%"))
                            .or(a2.threeCode.like("%" + request.getName() + "%"))
                            .or(a2.fourCode.like("%" + request.getName() + "%"))
                            .or(a2.airportName.like("%" + request.getName() + "%"))
                            .or(a2.airportShortName.like("%" + request.getName() + "%"))
            );
        }

//        QueryResults<AirportConfineVO> fetchResults =
        List<AirportConfineVO> fetch = queryFactory.select(
//                Projections.constructor(
                Projections.bean(
                        AirportConfineVO.class, ac.id, ac.apartDay, ac.status, ac.remark
                        , Expressions.stringTemplate("concat('[', group_concat(concat('{\"three_code\":\"', {0}, '\", \"airport_name\":\"',{1}, '\"}')),']') ", a.threeCode, a.airportName).as("fly_past_airports")
                        , Expressions.stringTemplate("concat('[', group_concat(concat('{\"three_code\":\"', {0}, '\", \"airport_name\":\"', {1}, '\"}')),']') ", a2.threeCode, a2.airportName).as("no_fly_airports")
                )
        )
//                .from(ac,a)
                .from(ac).leftJoin(a)
                .on(Expressions.booleanTemplate("json_contains({0}, concat('\"', {1}, '\"'), '$')=true", ac.flyPastAirportCodeJson, a.threeCode))
                .leftJoin(a2)
                .on(Expressions.booleanTemplate("json_contains({0}, concat('\"', {1}, '\"'), '$')=true", ac.noFlyAirportCodeJson, a2.threeCode))

                .groupBy(ac.id, ac.apartDay, ac.status, ac.remark)
                .where(
                        builder
//                .and(Expressions.booleanTemplate("json_contains({0}, concat('\"', {1}, '\"'), '$')=true", ac.flyPastAirportCodeJson, a.threeCode))
                                .getValue()
                )
//                .limit(pageable.getPageSize()).offset(pageable.getOffset())
//                .fetchResults();
                .fetch();
        return new PageImpl<>(fetch, pageable, fetch.size());
//        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }

    /**
     * complex join pagination query with complicated functions  <br/>
     * here use native sql that not recommended. <br/>
     * the jpa drawback comes in weak resultMapping<br/>
     * recommend MyBatis
     */
    @GetMapping
    public Page<AirportConfineVO> sql(AirportConfineSearch request, Pageable pageable) {
        String where = " ";
        if (request.getName() != null) {
            where += ("where ( a.three_code like concat('%',:name,'%')\n" +
                    "                or a.four_code like concat('%',:name,'%')\n" +
                    "                or a.airport_name like concat('%',:name,'%')\n" +
                    "                or a.airport_short_name like concat('%',:name,'%') )\n" +
                    "                or (\n" +
                    "                a2.three_code like concat('%',:name,'%')\n" +
                    "                or a2.four_code like concat('%',:name,'%')\n" +
                    "                or a2.airport_name like concat('%',:name,'%')\n" +
                    "                or a2.airport_short_name like concat('%',:name,'%') )");

        }
        //bug: 会导致AirportConfineVO.fly_past_airports中元素重复， 这里不解决此bug
        String sql = "select ac.id,ac.apart_day,ac.status,ac.remark, " +
                "       concat('[',GROUP_CONCAT(CONCAT('{\"three_code\":\"', a.three_code, '\", \"airport_name\":\"',a.airport_name ,'\"}')) ,']') as fly_past_airports, " +
                "       concat('[',GROUP_CONCAT(CONCAT('{\"three_code\":\"', a2.three_code, '\", \"airport_name\":\"',a2.airport_name ,'\"}')),']') as no_fly_airports " +
                " from airport_confine ac left join airport a on json_contains(ac.fly_past_airport_code_json,concat('\"',a.three_code,'\"'),'$') left join airport a2 on json_contains(ac.no_fly_airport_code_json,CONCAT('\"',a2.three_code,'\"'),'$')  " +
                where + " group by ac.id,ac.apart_day,ac.status,ac.remark limit :limit offset :offset";
        String countSql = "select count(distinct ac.id)\n" +
                "from airport_confine ac left join airport a on json_contains(ac.fly_past_airport_code_json,concat('\"',a.three_code,'\"'),'$') left join airport a2 on json_contains(ac.no_fly_airport_code_json,CONCAT('\"',a2.three_code,'\"'),'$') " +
                where + " group by ac.id,ac.apart_day,ac.status,ac.remark";

        Query query = entityManager.createNativeQuery(sql)
                .setParameter("limit", pageable.getPageSize())
                .setParameter("offset", pageable.getOffset())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(new AirportConfineVOResultTransformer());

        Query countQuery = entityManager.createNativeQuery(countSql);

        if (request.getName() != null) {
            query.setParameter("name", request.getName());
            //noinspection JpaQueryApiInspection
            countQuery.setParameter("name", request.getName());
        }

        List<AirportConfineVO> resultList = query.getResultList();

        int total = ((BigInteger) countQuery.getSingleResult()).intValue();

        return new PageImpl<>(resultList, pageable, total);
    }

    @Data
    public static class AirportConfineSearch {
        private String name;
        private Boolean status;
        private Integer apartDay;

    }

    @Data
    public static class AirportConfineVO {
        private static ObjectMapper objectMapper = new ObjectMapper();

        private String id;

        private Integer apart_day;
        private Integer apartDay;

        private Boolean status;

        private String remark;

        //        @Type(type = "json") //useless
        @JsonIgnore
        private String fly_past_airports;

        public void setFly_past_airports(String fly_past_airports) throws JsonProcessingException {
            this.flyPastAirports = objectMapper.readValue(fly_past_airports, new TypeReference<List<AirportBO>>() {
            });
        }

        private List<AirportBO> flyPastAirports;

        public void setFlyPastAirports(String flyPastAirports) throws JsonProcessingException {
            this.flyPastAirports = objectMapper.readValue(flyPastAirports, new TypeReference<List<AirportBO>>() {
            });
            ;
        }

        public void setNoFlyAirports(String noFlyAirports) throws JsonProcessingException {
            this.noFlyAirports = objectMapper.readValue(noFlyAirports, new TypeReference<List<AirportBO>>() {
            });
            ;
        }

        //        @Type(type = "json")
        @JsonIgnore
        private String no_fly_airports;

        public void setNo_fly_airports(String no_fly_airports) throws JsonProcessingException {
            this.noFlyAirports = objectMapper.readValue(no_fly_airports, new TypeReference<List<AirportBO>>() {
            });
        }

        private List<AirportBO> noFlyAirports;


        public AirportConfineVO() {
        }

        /**
         * for JPA DTO result:
         * <pre>
         *     {@code
         *             QueryResults<AirportConfineVO> fetchResults = queryFactory.select(Projections.constructor(AirportConfineVO.class, ac.id, ac.apartDay, ac.status, ac.remark
         *                 , Expressions.stringTemplate("concat('[', GROUP_CONCAT(CONCAT('{\"three_code\":\"', {0}, '\", \"airport_name\":\"', a.airport_name, '\"}')),']') as fly_past_airports", a.threeCode, a.airportName)
         *                 , Expressions.stringTemplate("concat('[', GROUP_CONCAT(CONCAT('{\"three_code\":\"', {0}, '\", \"airport_name\":\"', a.airport_name, '\"}')),']') as fly_past_airports", a2.threeCode, a2.airportName))
         *         )
         *     }
         * </pre>
         */
        public AirportConfineVO(String id, Integer apart_day, Boolean status, String remark, String fly_past_airports, String no_fly_airports) throws JsonProcessingException {
            this.id = id;
            this.apart_day = apart_day;
            this.status = status;
            this.remark = remark;
            this.flyPastAirports = objectMapper.readValue(fly_past_airports, new TypeReference<List<AirportBO>>() {
            });
            this.noFlyAirports = objectMapper.readValue(no_fly_airports, new TypeReference<List<AirportBO>>() {
            });
        }

        /**
         * for JPA DTO result:
         * <pre>
         *     {@code
         *             QueryResults<AirportConfineVO> fetchResults = queryFactory.select(Projections.constructor(AirportConfineVO.class, ac.id, ac.apartDay, ac.status, ac.remark
         *                 , Expressions.stringTemplate("concat('[', GROUP_CONCAT(CONCAT('{\"three_code\":\"', {0}, '\", \"airport_name\":\"', a.airport_name, '\"}')),']') as fly_past_airports", a.threeCode, a.airportName)
         *         )
         *     }
         * </pre>
         */
        public AirportConfineVO(String id, Integer apart_day, Boolean status, String remark, String fly_past_airports) throws JsonProcessingException {
            this.id = id;
            this.apart_day = apart_day;
            this.status = status;
            this.remark = remark;
            this.flyPastAirports = objectMapper.readValue(fly_past_airports, new TypeReference<List<AirportBO>>() {
            });
        }


        public AirportConfineVO(
                Object[] tuple,
                Map<String, Integer> aliasToIndexMap) {

            this.id = (String) tuple[aliasToIndexMap.get("id")];
            this.apart_day = (Integer) tuple[aliasToIndexMap.get("apart_day")];
            this.status = (Boolean) tuple[aliasToIndexMap.get("status")];
            this.remark = (String) tuple[aliasToIndexMap.get("remark")];
            try {
                this.flyPastAirports = objectMapper.readValue((String) tuple[aliasToIndexMap.get("fly_past_airports")], new TypeReference<List<AirportBO>>() {
                });
                this.noFlyAirports = objectMapper.readValue((String) tuple[aliasToIndexMap.get("no_fly_airports")], new TypeReference<List<AirportBO>>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }


        }
    }

    public static class AirportConfineVOResultTransformer implements ResultTransformer {

        @Override
        public Object transformTuple(Object[] tuple, String[] aliases) {
            Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);
            String id = (String) tuple[aliasToIndexMap.get("id")];
            return new AirportConfineVO(tuple, aliasToIndexMap);
        }

        @Override
        public List transformList(List collection) {
            return collection;
        }

        public Map<String, Integer> aliasToIndexMap(
                String[] aliases) {

            Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();

            for (int i = 0; i < aliases.length; i++) {
                aliasToIndexMap.put(aliases[i], i);
            }

            return aliasToIndexMap;
        }
    }

    @Data
    public static class AirportBO {

        private String three_code;

        private String four_code;

        private String airport_name;

        private String airport_short_name;

    }

}
