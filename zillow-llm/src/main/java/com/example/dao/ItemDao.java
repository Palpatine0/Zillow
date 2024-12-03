package com.example.dao;


import com.example.entity.Item;

import java.util.List;
import java.util.Map;

public interface ItemDao {

    List<Item> findItemsByCriteria(Map<String, Object> criteria);

}
