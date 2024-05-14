package com.example.controller;

import com.example.service.OrderService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrdersByUserId")
    public ZillowResult getOrdersByUserId(@RequestParam String userId) {
        return orderService.getOrdersByUserId(userId);
    }


}
