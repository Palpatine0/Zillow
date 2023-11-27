package com.example.dao;

import com.example.entity.Item;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface TrendyDao {
    List<Item> getTrendy(Query query);
}

