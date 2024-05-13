package com.example.controller;

import com.example.service.TrendyService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trendy")
public class TrendyController {
    @Autowired
    private TrendyService trendyService;

    @GetMapping("/getTrendies")
    public ZillowResult getTrendies(String city) {
        return trendyService.getTrendies(city);
    }


}
