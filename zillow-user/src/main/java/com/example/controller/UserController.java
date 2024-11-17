package com.example.controller;


import com.example.entity.User;
import com.example.service.UserService;
import com.example.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public BaseResult register(String id, String username, String password, String phone, String avatar, Integer role) {
        if (id==null) {
            return BaseResult.error("Empty user");
        }
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setAvatar(avatar);
        user.setRole(role);
        return userService.register(user);
    }

    @PostMapping("/login")
    public BaseResult login(String username, String password, String phone, String verificationCode) {
        return userService.login(username, password, phone, verificationCode);
    }

    @PostMapping("/deleteUserById")
    public BaseResult deleteUserById(String id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/getUsers")
    public BaseResult getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/sendVerificationCode")
    public BaseResult sentVerificationCode(String phone) {
        return userService.sendVerificationCode(phone);
    }

    @GetMapping("/getLoggedInUser")
    public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    @GetMapping("/getUserByUsername")
    public BaseResult getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/getUserById")
    public BaseResult getUserById(String id) {
        return userService.getUserById(id);
    }
}
