package com.example.controller;


import com.example.service.LoginService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public ZillowResult register(String username, String password, String phone) {
        return loginService.register(username, password, phone);
    }

    @PostMapping("/login")
    public ZillowResult login(String username, String password, String phone, String verificationCode) {
        System.out.println();
        return loginService.login(username, password, phone, verificationCode);
    }

    @PermitAll
    @GetMapping("/getUser")
    public ZillowResult getUser() {
        return loginService.getUser();
    }

    @PostMapping("/sendVerificationCode")
    public ZillowResult sentVerificationCode(String phone) {
        return loginService.sendVerificationCode(phone);
    }

    @GetMapping("/getLoggedInUser")
    public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

}
