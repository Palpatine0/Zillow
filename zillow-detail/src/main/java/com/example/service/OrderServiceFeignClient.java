package com.example.service;

import com.example.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "zillow-order")
public interface OrderServiceFeignClient {
    @GetMapping("/order/getOrder")
    public List<Order> getOrder(@RequestParam("phone") String phone);

}
