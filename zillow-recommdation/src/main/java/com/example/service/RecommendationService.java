package com.example.service;

import com.example.vo.ZillowResult;

public interface RecommendationService {
    ZillowResult getRecommendations(String city);
}
