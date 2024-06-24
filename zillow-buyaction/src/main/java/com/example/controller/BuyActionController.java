package com.example.controller;


import com.example.service.BuyActionService;
import com.example.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buyAction")
public class BuyActionController {
    @Autowired
    private BuyActionService buyActionService;

    @GetMapping("/buyAction")
    public BaseResult buyAction(String itemId, String userId, String startDate, String endDate, String price) {
        return buyActionService.buyAction(itemId, userId, startDate, endDate, price);
    }

}
