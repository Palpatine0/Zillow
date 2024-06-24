package com.example.service;

import com.example.vo.BaseResult;

public interface RecommendationService {
    BaseResult getRecommendations(String city);
}
