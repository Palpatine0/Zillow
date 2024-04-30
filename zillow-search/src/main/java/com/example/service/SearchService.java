package com.example.service;

import com.example.vo.ZillowResult;

public interface SearchService {
    ZillowResult searchByKeyWord(String city, String content, int page, int rows);

    ZillowResult searchByCity(String city, int page, int rows);
    void ESInit();
}
