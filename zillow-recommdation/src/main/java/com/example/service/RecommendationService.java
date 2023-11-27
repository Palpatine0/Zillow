package com.example.service;

import com.example.vo.ZillowResult;

public interface RecommendationService {
    ZillowResult getRecommendation(String city);
}
