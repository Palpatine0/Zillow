package com.example.dao.impl;

import com.example.dao.DetailDao;
import com.example.entity.Banner;
import com.example.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetailDaoImpl implements DetailDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Item findItemById(String id) {
        return mongoTemplate.findById(id,Item.class);
    }
}
