package com.example.controller;


import com.example.entity.Item;
import com.example.entity.Order;
import com.example.service.DetailService;
import com.example.service.OrderServiceFeignClient;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class DetailController {
    @Autowired
    private DetailService detailService;

    @Autowired(required = false)
    private OrderServiceFeignClient orderServiceFeignClient;

    @GetMapping("/getItem")
    public Item getItem(String id) {
        return detailService.getItem(id);
    }

    @PostMapping("/addItem")
    public ZillowResult addItem(@RequestBody Item item) {
        return detailService.addItem(item);
    }

    @GetMapping("/getOrder")
    public List<Order> selectOrder(String phone) {
        return orderServiceFeignClient.getOrder(phone);
    }


}
