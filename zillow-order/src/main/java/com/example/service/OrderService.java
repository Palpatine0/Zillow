package com.example.service;

import com.example.vo.BaseResult;

public interface OrderService {
    BaseResult getOrdersByUserId(String phone);
}
