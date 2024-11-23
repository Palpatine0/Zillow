package com.example.dao;


import com.example.entity.Item;

import java.util.ArrayList;
import java.util.List;

public interface ItemDao {
    Item findItemById(String id);

    List<Item> findItemByCity(String city);

    int countItemByCity(String city);

}
