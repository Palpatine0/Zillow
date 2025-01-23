package com.example.service;

import com.example.vo.BaseResult;

public interface SearchService {
    BaseResult searchByKeyWord(String city, String content, int page, int rows);

    BaseResult searchByCity(String city, int page, int rows);

    void esinit();
}
