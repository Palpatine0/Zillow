package com.example.service;

import com.example.entity.User;
import com.example.vo.BaseResult;

public interface UserService {
    BaseResult sendVerificationCode(String phone);

    BaseResult login(String username, String password, String phone, String verificationCode);

    BaseResult register(User user);

    BaseResult deleteUserById(String id);

    BaseResult getUsers();

    BaseResult getUserByUsername(String username);

    BaseResult getUserById(String id);

}
