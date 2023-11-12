package com.example.service;

import com.example.vo.ZillowResult;

public interface SearchService {
    ZillowResult search(String city, String content,int page,int rows);
    void ESInit();
}
