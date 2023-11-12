package com.example.controller;


import com.example.entity.Item;
import com.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detail")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/getDetail")
    public Item getDetails(String id){
        return loginService.getDetail(id);
    }
}
