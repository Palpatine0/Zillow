package com.example.dao;


import com.example.entity.Item;

public interface ItemDao {
    Item findItemById(String id);

    void saveItem(Item item);
}
