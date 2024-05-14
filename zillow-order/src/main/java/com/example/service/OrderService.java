package com.example.service;

import com.example.entity.Order;
import com.example.vo.ZillowResult;

import java.util.List;

public interface OrderService {
    ZillowResult getOrders(String phone);
}
