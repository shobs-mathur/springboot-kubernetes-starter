package com.shobs.springboot.springenvers.service;

import com.shobs.springboot.springenvers.domain.SequenceNumber;
import com.shobs.springboot.springenvers.repository.SequenceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
public class CounterService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private SequenceRepository sequenceRepository;

    private static String redisCounterKey = "REDIS_COUNTER";

    public Long incrementRedisCounterLoop() {
        redisService.create(redisCounterKey);
        String value = redisService.get(redisCounterKey);
        log.info("value for key {}", value);
        long totalMillis = 0;
        for(int i= 0; i<=50; i++) {
            Instant timeStampBefore =Instant.now();
            Long newValue = redisService.increment(redisCounterKey);
            log.info("newValue {}", newValue);
            Instant timeStampAfter = Instant.now();
            long millis = ChronoUnit.MILLIS.between(timeStampBefore, timeStampAfter);
            totalMillis = totalMillis + millis;
        }
        log.info("total time taken {}", totalMillis);
        return totalMillis;
    }

    public Long incrementRdsCounter() {
        long totalMillis = 0;
        for(int i= 0; i<=50; i++) {
            Instant timeStampBefore = Instant.now();
            createSequenceNumber();
            Instant timeStampAfter = Instant.now();
            long millis = ChronoUnit.MILLIS.between(timeStampBefore, timeStampAfter);
            totalMillis = totalMillis + millis;
        }
        log.info("total time taken {}", totalMillis);
        return totalMillis;
    }

    public String createSequenceNumber() {
        SequenceNumber generatedSequenceNumber = sequenceRepository.save(SequenceNumber.builder()
                .createDate(Timestamp.from(Instant.now())).build());
        return String.format("%06d", generatedSequenceNumber.getSequenceNumber());
    }


    public Long incrementRedisCounter() {
        Instant timeStampBefore =Instant.now();
        Long newValue = redisService.increment(redisCounterKey);
        Instant timeStampAfter = Instant.now();
        long millis = ChronoUnit.MILLIS.between(timeStampBefore, timeStampAfter);
        log.info("total time taken {}, newValue{}", millis, newValue);
        return newValue;
    }

    public void setCounter() {
        redisService.create(redisCounterKey);
    }
}
