package com.example.service;

import com.example.vo.ZillowResult;

public interface UserService {
    ZillowResult sendVerificationCode(String phone);

    ZillowResult login(String username, String password, String phone, String verificationCode);

    ZillowResult register(String username, String password, String phone);

    ZillowResult deleteUserById(String id);

    ZillowResult getUsers();

    ZillowResult getUserByUsername(String username);

    ZillowResult getUserById(String id);

}
