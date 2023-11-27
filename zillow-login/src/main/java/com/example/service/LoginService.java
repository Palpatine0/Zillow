package com.example.service;

import com.example.vo.ZillowResult;

public interface LoginService {
    ZillowResult sendVerificationCode(String phone);

    ZillowResult login(String phone, String verificationCode);
}
