package com.example.dao.impl;


import com.example.dao.RecommendationDao;
import com.example.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecommendationDaoImpl implements RecommendationDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Item> selectRecommendation(Query query) {
        return mongoTemplate.find(query,Item.class);
    }

 }
