package com.example.dao;

import com.example.entity.Item4ES;

import java.util.List;

public interface SearchDao {
    List<Item4ES> searchByKeyWord(String city, String content, int page, int rows);

    List<Item4ES> searchAll(String city, int page, int rows);

    void batchInsertToES(List<Item4ES> items);
}

