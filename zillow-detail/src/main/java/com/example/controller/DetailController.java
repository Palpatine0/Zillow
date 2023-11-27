package com.example.controller;


import com.example.entity.Item;
import com.example.entity.Order;
import com.example.service.DetailService;
import com.example.service.OrderServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/detail")
public class DetailController {
    @Autowired
    private DetailService detailService;

    @Autowired(required = false)
    private OrderServiceFeignClient orderServiceFeignClient;

    @GetMapping("/getDetail")
    public Item getDetails(String id) {
        return detailService.getDetail(id);
    }

    @GetMapping("/getOrder")
    public List<Order> selectOrder(String phone) {
        return orderServiceFeignClient.getOrder(phone);
    }

}
