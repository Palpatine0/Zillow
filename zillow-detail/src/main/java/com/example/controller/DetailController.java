package com.example.controller;


import com.example.entity.Item;
import com.example.service.DetailService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detail")
public class DetailController {
    @Autowired
    private DetailService detailService;

    @GetMapping("/getDetail")
    public Item getDetails(String id){
        return detailService.getDetail(id);
    }
}
