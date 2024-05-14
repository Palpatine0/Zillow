package com.example.dao.impl;


import com.example.dao.OrderDao;
import com.example.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Order> findOrders(Query query) {

        return mongoTemplate.find(query, Order.class);
    }
}
