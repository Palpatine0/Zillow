package com.example.service;

import com.example.vo.ZillowResult;

public interface BuyActionService {
    ZillowResult buyAction(String itemId, String userId,String startDate,String endDate,String price);
}
