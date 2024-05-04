package com.example.dao.impl;


import com.example.dao.TrendyDao;
import com.example.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrendyDaoImpl implements TrendyDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Item> getTrendy(Query query) {
        return mongoTemplate.find(query,Item.class);
    }
}
