package com.example.controller;


import com.example.entity.Item;
import com.example.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/detail")
public class DetailController {
    @Autowired
    private DetailService detailService;

    //    @GetMapping("/getDetail")
    @GetMapping("/details")
    public Item getDetails(String id) {
        return detailService.getDetail(id);
    }
}
