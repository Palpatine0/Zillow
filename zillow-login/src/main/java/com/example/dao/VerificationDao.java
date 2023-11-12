package com.example.dao;


public interface VerificationDao {
    void setVerificationCode(String key,Object value);
    void getVerificationCode(String key);
    void deleteVerificationCode(String key);

}
