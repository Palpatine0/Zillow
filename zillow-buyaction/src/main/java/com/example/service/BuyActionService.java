package com.example.service;

import com.example.vo.BaseResult;

public interface BuyActionService {
    BaseResult buyAction(String itemId, String userId, String startDate, String endDate, String price);
}
