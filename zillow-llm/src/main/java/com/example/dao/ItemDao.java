package com.example.dao;


import com.example.entity.Item;

import java.util.List;

public interface ItemDao {

    List<Item> findItemsByCity(String city);

    List<Item> findItems();

}
