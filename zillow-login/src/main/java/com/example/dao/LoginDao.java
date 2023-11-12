package com.example.dao;


import com.example.entity.Item;

public interface LoginDao {
    Item findItemById(String id);
}
