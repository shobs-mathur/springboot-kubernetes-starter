package com.shobs.springboot.springenvers.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Slf4j
@Component
public class RedisService {
    private StringRedisTemplate redisTemplate;

    @Autowired
    RedisService (@Qualifier("REDIS_TEMPLATE")StringRedisTemplate template) {
        this.redisTemplate = template;
    }

    public Long increment(String key) {
        return redisTemplate.boundValueOps(key).increment();
    }

    public void create(String key) {
        redisTemplate.boundValueOps(key).set(String.valueOf(1));
    }

    public String get(String key) {
        return redisTemplate.boundValueOps(key).get();
    }
}
