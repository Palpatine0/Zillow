package com.example.dao;

import com.example.entity.Item;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ItemDao {
    List<Item> selectItem(Query query);
}

