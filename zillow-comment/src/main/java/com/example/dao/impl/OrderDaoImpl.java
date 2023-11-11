package com.example.dao.impl;

import com.example.dao.OrderDao;
import com.example.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateCommentStatus(String orderId, int commentState) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(orderId));
        Update update = Update.update("commentState", commentState);
        mongoTemplate.updateFirst(query,update, Order.class);
    }
}
