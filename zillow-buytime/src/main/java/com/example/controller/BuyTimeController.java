package com.example.controller;


import com.example.service.BuyTimeService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buyTime")
public class BuyTimeController {
    @Autowired
    private BuyTimeService buyTimeService;

    @GetMapping("/getBuyTime")
    public ZillowResult getBuyTime(@RequestParam(value = "id") String itemId) {
        return buyTimeService.getBuyTime(itemId);
    }

}
