package com.example.dao;


import com.example.entity.Order;

public interface OrderDao {
    void updateCommentStatus(String orderId, int commentStatus);

    public Order getOrderByOrderId(String orderId);

}
