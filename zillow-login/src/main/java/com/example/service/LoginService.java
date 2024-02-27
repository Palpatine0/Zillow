package com.example.service;

import com.example.vo.ZillowResult;

public interface LoginService {
    ZillowResult sendVerificationCode(String phone);

    ZillowResult login(String username, String password, String phone, String verificationCode);

    ZillowResult register(String username, String password, String phone);
}
