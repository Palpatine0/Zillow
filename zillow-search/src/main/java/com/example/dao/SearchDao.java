package com.example.dao;

import com.example.entity.Item4ES;

import java.util.List;

public interface SearchDao {
    void batchInsertToES(List<Item4ES> items);

    List<Item4ES> searchByKeyWord(String city, String content, int page, int rows);
}

