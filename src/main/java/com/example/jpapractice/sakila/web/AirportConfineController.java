package com.example.jpapractice.sakila.web;

import com.example.jpapractice.sakila.model.AirportConfine;
import com.example.jpapractice.sakila.repository.AirportConfineRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Luo Bao Ding
 * @since 1/8/2021
 */
@RestController
@RequestMapping("airportConfine")
@RequiredArgsConstructor
public class AirportConfineController {

    private final AirportConfineRepository repository;

    private final EntityManager entityManager;


    @PostMapping
    public AirportConfine create(AirportConfine request) {
        return repository.save(request);
    }

    /**
     * complex join pagination query with complicated functions  <br/>
     * here use native sql that not recommended. <br/>
     * the jpa drawback comes in weak resultMapping<br/>
     * recommend MyBatis
     */
    @GetMapping
    public Page<AirportConfineVO> search(AirportConfineSearch request, Pageable pageable) {
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

        //        @Type(type = "json") //useless
        private List<AirportBO> fly_past_airports = new ArrayList<>();

        private Integer apart_day;

        //        @Type(type = "json")
        private List<AirportBO> no_fly_airports = new ArrayList<>();

        private Boolean status;

        private String remark;

        public AirportConfineVO(
                Object[] tuple,
                Map<String, Integer> aliasToIndexMap) {

            this.id = (String) tuple[aliasToIndexMap.get("id")];
            this.apart_day = (Integer) tuple[aliasToIndexMap.get("apart_day")];
            this.status = (Boolean) tuple[aliasToIndexMap.get("status")];
            this.remark = (String) tuple[aliasToIndexMap.get("remark")];
            try {
                this.fly_past_airports = objectMapper.readValue((String) tuple[aliasToIndexMap.get("fly_past_airports")], new TypeReference<List<AirportBO>>() {
                });
                this.no_fly_airports = objectMapper.readValue((String) tuple[aliasToIndexMap.get("no_fly_airports")], new TypeReference<List<AirportBO>>() {
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
