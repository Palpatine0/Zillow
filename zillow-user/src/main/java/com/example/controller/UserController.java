package com.example.controller;


import com.example.service.UserService;
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
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ZillowResult register(String username, String password, String phone) {
        return userService.register(username, password, phone);
    }

    @PostMapping("/login")
    public ZillowResult login(String username, String password, String phone, String verificationCode) {
        return userService.login(username, password, phone, verificationCode);
    }

    @PostMapping("/deleteUser")
    public ZillowResult deleteUser(String id) {
        return userService.deleteUser(id);
    }

    @PermitAll
    @GetMapping("/getUser")
    public ZillowResult getUser() {
        return userService.getUser();
    }

    @PostMapping("/sendVerificationCode")
    public ZillowResult sentVerificationCode(String phone) {
        return userService.sendVerificationCode(phone);
    }

    @GetMapping("/getLoggedInUser")
    public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

}
