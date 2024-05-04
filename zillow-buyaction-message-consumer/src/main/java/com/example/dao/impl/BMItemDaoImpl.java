package com.example.dao.impl;

import com.example.dao.BMItemDao;
import com.example.entity.Item;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class BMItemDaoImpl implements BMItemDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public long updateItem(String itemId, Boolean isRented) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(itemId));
        Update update = Update.update("isRented", isRented);
        UpdateResult result = mongoTemplate.updateFirst(query, update, Item.class);
        return result.getModifiedCount();
    }
}
