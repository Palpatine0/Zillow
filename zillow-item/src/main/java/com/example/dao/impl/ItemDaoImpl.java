package com.example.dao.impl;

import com.example.dao.ItemDao;
import com.example.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Item findItemById(String id) {
        return mongoTemplate.findById(id, Item.class);
    }

    @Override
    public List<Item> findItemByCity(String city, int page, int rows) {
        Query query = new Query();
        query.addCriteria(Criteria.where("city").is(city));
        query.skip((long) (page) * rows);
        query.limit(rows);
        return mongoTemplate.find(query, Item.class);
    }

    @Override
    public int countItemByCity(String city) {
        Query query = new Query();
        query.addCriteria(Criteria.where("city").is(city));
        return (int) mongoTemplate.count(query, Item.class);
    }


    @Override
    public void saveItem(Item item) {
        mongoTemplate.save(item);
    }

    @Override
    public void updateItemStatusById(String id, Boolean isRented, Boolean recommendation) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        if (isRented != null) {
            update.set("isRented", isRented);
        }
        if (recommendation != null) {
            update.set("recommendation", recommendation);
        }
        mongoTemplate.findAndModify(query, update, Item.class);
    }

    @Override
    public void updateItemInfoById(String id, Item item) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("title", item.getTitle());
        update.set("sales", item.getSales());
        update.set("recommendation", item.getRecommendation());
        update.set("weight", item.getWeight());
        update.set("price", item.getPrice());
        update.set("city", item.getCity());
        update.set("rentType", item.getRentType());
        update.set("houseType", item.getHouseType());

        Map<String, String> info = item.getInfo();
        if (info != null) {
            for (Map.Entry<String, String> entry : info.entrySet()) {
                update.set("info." + entry.getKey(), entry.getValue());
            }
        }

        update.set("buytime", item.getBuytime());
        update.set("isRented", item.getIsRented());
        update.set("imgs", item.getImgs());
        mongoTemplate.findAndModify(query, update, Item.class);
    }


}
