package com.example.dao.impl;

import com.example.dao.BMOrderDao;
import com.example.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class BMOrderDaoImplImpl implements BMOrderDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveOrder(Order order) {
        mongoTemplate.save(order);
    }
}
