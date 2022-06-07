package com.shobs.springboot.springenvers.controller;

import com.shobs.springboot.springenvers.service.CounterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/counter")
@Slf4j
public class Controller {

    private CounterService counterService;

    public Controller(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping(path = "redis")
    public Long getRedisCounter() {
        return counterService.incrementRedisCounterLoop();
    }

    @GetMapping(path = "rds")
    public Long getRdsCounter() {
        return counterService.incrementRdsCounter();
    }

    @GetMapping(path = "redis2")
    public Long incrementRedisCounter() {
        return counterService.incrementRedisCounter();
    }

    @GetMapping(path = "redis/create")
    public void createRedisCounter() {
        counterService.setCounter();
    }
}