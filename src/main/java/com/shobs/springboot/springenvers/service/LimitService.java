package com.shobs.springboot.springenvers.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.shobs.springboot.springenvers.domain.Limit;
import com.shobs.springboot.springenvers.domain.LimitEntity;
import com.shobs.springboot.springenvers.repository.LimitEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LimitService {

    public static final String LIMITS_JSON = "limits.json";

    private LimitEntityRepository limitEntityRepository;


    public LimitService(LimitEntityRepository limitEntityRepository) {
        this.limitEntityRepository = limitEntityRepository;
    }

    public List<Limit> getLimits() {
        List<Limit> limits = new ArrayList<>();

        limitEntityRepository.findAll().forEach(l ->
                limits.add(Limit.builder().limit(l.getLimitKey()).amount(l.getAmount()).build())
        );
        return limits;
    }

    public Limit getLimit(String limit) {
        return limitEntityRepository.findByLimitKey(limit)
                .map(l -> Limit.builder().limit(limit).amount(l.getAmount()).build()).orElse(null);
    }

    public void update(List<Limit> limits) {
        Map<String, BigDecimal> limitMap = limits.stream().collect(
                Collectors.toMap(Limit::getLimit, Limit::getAmount, (o, n) -> n));

        limitEntityRepository.findAll().forEach(l -> {
                    if (limitMap.containsKey(l.getLimitKey())) {
                        l.setAmount(limitMap.get(l.getLimitKey()));
                        limitEntityRepository.save(l);
                    }
                }
        );
    }

    public List<Limit> reLoadLimitConfig() {
        List<Limit> limits = loadList(LIMITS_JSON, Limit.class);
        if (limits != null) {
            Map<String, BigDecimal> limitMap = limits.stream().collect(
                    Collectors.toMap(Limit::getLimit, Limit::getAmount, (o, n) -> n));

            Set<String> existLimits = new HashSet<>();
            limitEntityRepository.findAll().forEach(l -> {
                        if (!limitMap.containsKey(l.getLimitKey())) {
                            log.info("remove limit: {}", l.getLimitKey());
                            limitEntityRepository.delete(l);
                        } else {
                            existLimits.add(l.getLimitKey());
                        }
                    }
            );

            limits.forEach(l -> {
                if (!existLimits.contains(l.getLimit())) {
                    log.info("add new limit: {}", l.getLimit());
                    limitEntityRepository.save(LimitEntity.builder()
                            .amount(l.getAmount())
                            .limitKey(l.getLimit()).build());
                }
            });
        }
        return limits;
    }

    public static <T> List<T> loadList(String fileName, java.lang.Class<T> valueType) {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try {
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, valueType);
            List<T> objectList = objectMapper.readValue(classloader.getResourceAsStream(fileName), listType);
            return objectList;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
