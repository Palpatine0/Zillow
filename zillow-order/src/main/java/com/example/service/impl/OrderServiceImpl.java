package com.example.service.impl;

import com.example.dao.OrderDao;
import com.example.entity.Order;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;


    @Override
    public List<Order> getOrder(String phone) {
        List<Order> orders = orderDao.getOrders(phone);
        return orders;
    }
}
