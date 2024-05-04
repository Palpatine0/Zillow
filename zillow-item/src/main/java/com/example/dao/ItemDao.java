package com.example.dao;


import com.example.entity.Item;

import java.util.ArrayList;
import java.util.List;

public interface ItemDao {
    Item findItemById(String id);

    List<Item> findItemByCity(String city, int page, int rows);

    int countItemByCity(String city);

    void saveItem(Item item);

    void updateItemStatusById(String id, Boolean isRented, Boolean recommendation);

    void updateItemInfoById(String id, Item item);

    void updateItemShowcasesById(String id, ArrayList<String> imgs);


}
