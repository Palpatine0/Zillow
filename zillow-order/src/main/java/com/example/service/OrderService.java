package com.example.service;

import com.example.vo.ZillowResult;

public interface OrderService {
    ZillowResult getOrdersByUserId(String phone);
}
