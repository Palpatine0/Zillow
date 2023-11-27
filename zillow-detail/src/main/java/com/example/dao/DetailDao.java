package com.example.dao;


import com.example.entity.Item;

public interface DetailDao {
    Item findItemById(String id);
}
