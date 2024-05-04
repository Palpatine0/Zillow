package com.example.dao.impl;

import com.example.dao.BuyTimeDao;
import com.example.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BuyTimeDaoImpl implements BuyTimeDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Item getItem(String itemId) {
        return mongoTemplate.findById(itemId, Item.class);
    }
}
