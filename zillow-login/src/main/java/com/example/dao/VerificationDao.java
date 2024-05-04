package com.example.dao;


import com.example.entity.VerificationCode;

public interface VerificationDao {
    void setVerificationCode(String key,Object value);

    VerificationCode getVerificationCode(String key);

    Boolean deleteVerificationCode(String key);

}
