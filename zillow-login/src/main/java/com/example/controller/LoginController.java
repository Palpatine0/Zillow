package com.example.controller;


import com.example.service.LoginService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    //    @PostMapping("/sendVerificationCode")
    @PostMapping("/sendyzm")
    public ZillowResult sentVerificationCode(String phone) {
        return loginService.sendVerificationCode(phone);
    }

    //    @PostMapping("/login")
    @PostMapping("/login")
    public ZillowResult login(String phone, String verificationCode) {
        return loginService.login(phone, verificationCode);
    }

    @GetMapping("/getLoggedInUser")
    public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

}
