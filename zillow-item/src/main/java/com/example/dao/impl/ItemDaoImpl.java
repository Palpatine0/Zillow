package com.example.dao.impl;

import com.example.dao.ItemDao;
import com.example.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Item findItemById(String id) {
        return mongoTemplate.findById(id, Item.class);
    }

    @Override
    public void saveItem(Item item) {
        mongoTemplate.save(item);
    }
}
