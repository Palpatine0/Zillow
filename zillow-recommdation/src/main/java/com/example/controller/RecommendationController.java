package com.example.controller;

import com.example.service.RecommendationService;
import com.example.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/getRecommendations")
    public BaseResult getRecommendations(String city) {
        return recommendationService.getRecommendations(city);
    }


}
