package com.example.dao;


import com.example.entity.LoginLog;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginLogDao {
    void insertLoginLog(LoginLog loginLog);
}
