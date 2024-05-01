package com.example.controller;


import com.example.entity.Item;
import com.example.entity.Order;
import com.example.service.ItemService;
import com.example.service.OrderServiceFeignClient;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired(required = false)
    private OrderServiceFeignClient orderServiceFeignClient;

    @GetMapping("/getItem")
    public Item getItem(String id) {
        return itemService.getItem(id);
    }

    @PostMapping("/addItem")
    public ZillowResult addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @GetMapping("/getOrder")
    public List<Order> selectOrder(String phone) {
        return orderServiceFeignClient.getOrder(phone);
    }


}
