package com.example.dao;

import com.example.entity.Order;

import java.util.List;

public interface OrderDao {

    List<Order> findOrdersByUserId(String userId);
}

