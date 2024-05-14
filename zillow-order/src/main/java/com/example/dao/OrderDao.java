package com.example.dao;

import com.example.entity.Order;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface OrderDao {

    List<Order> findOrders(Query query);
}

