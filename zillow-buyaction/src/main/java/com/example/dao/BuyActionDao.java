package com.example.dao;


import com.example.entity.Item;

public interface BuyActionDao {
    Item getItem(String key_itemId);
}
