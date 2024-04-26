package com.example.dao.impl;

import com.example.dao.LoginLogDao;
import com.example.entity.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDaoImpl implements LoginLogDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insertLoginLog(LoginLog loginLog) {
        mongoTemplate.save(loginLog);
    }
}
