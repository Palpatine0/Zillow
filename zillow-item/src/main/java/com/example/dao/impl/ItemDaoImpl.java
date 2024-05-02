package com.example.dao.impl;

import com.example.dao.ItemDao;
import com.example.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Item findItemById(String id) {
        return mongoTemplate.findById(id, Item.class);
    }

    @Override
    public List<Item> getItemByCity(String city, int page, int rows) {
        Query query = new Query();
        query.addCriteria(Criteria.where("city").is(city));
        query.skip((long) (page) * rows);
        query.limit(rows);
        return mongoTemplate.find(query, Item.class);
    }

    @Override
    public void saveItem(Item item) {
        mongoTemplate.save(item);
    }
}
