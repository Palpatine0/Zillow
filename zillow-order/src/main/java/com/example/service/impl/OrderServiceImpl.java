package com.example.service.impl;

import com.example.dao.OrderDao;
import com.example.entity.Order;
import com.example.service.OrderService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;


    @Override
    public ZillowResult getOrders(String userId) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("userId").is(userId)
        );
        query.addCriteria(criteria);
        List<Order> orders = orderDao.findOrders(query);
        return ZillowResult.ok(orders);
    }
}
