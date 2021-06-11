package com.shobs.springboot.springenvers.controller;


import com.shobs.springboot.springenvers.domain.Limit;
import com.shobs.springboot.springenvers.service.LimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/limit")
@Slf4j
public class LimitController {

    private LimitService limitService;

    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }

    /*
    Use this endpoint to update limits to the repository from file
    Same request body -
    [
        {
            "limit": "FINANCIAL_LIMIT_3",
            "amount": 300000.00
        }
    ]
    */
    @PatchMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody List<Limit> limits) {
        limitService.update(limits);
    }

    /*
        Use this endpoint to get all limits from the repository
    */

    @GetMapping(path = "")
    public List<Limit> getList() {
        return limitService.getLimits();
    }

    /*
    Use this endpoint to add limits to the repository from file
    */

    @GetMapping(path = "/add")
    public List<Limit> addList() {
        return limitService.reLoadLimitConfig();
    }

    /*
        Use this endpoint to get limit by limit name
        eg - FINANCIAL_LIMIT_1
    */
    @GetMapping(path = "/{limit}")
    public Limit getLimit(@PathVariable("limit") String limitKey) {
        return limitService.getLimit(limitKey);
    }
}