package com.example.service;

public interface BuyActionMessageService {
    boolean buyAction(String itemId, String userId,String startDate,String endDate,String price);
}
