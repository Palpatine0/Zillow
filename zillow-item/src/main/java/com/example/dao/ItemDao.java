package com.example.dao;


import com.example.entity.Item;

import java.util.List;

public interface ItemDao {
    Item findItemById(String id);

    List<Item> getItemByCity(String city, int page, int rows);

    void saveItem(Item item);
}
