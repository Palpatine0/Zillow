package com.example.dao.impl;

import com.example.dao.VerificationDao;
import com.example.entity.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;


@Repository
public class VerificationDaoImpl implements VerificationDao {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void setVerificationCode(String key, Object value) {
        // set the k and v. Thr v will be deleted after 2 min
        redisTemplate.opsForValue().set(key, value, Duration.ofMinutes(10L));
    }

    @Override
    public VerificationCode getVerificationCode(String key) {
        VerificationCode val = (VerificationCode) redisTemplate.opsForValue().get(key);
        return val;
    }

    @Override
    public Boolean deleteVerificationCode(String key) {
        return redisTemplate.delete(key);
    }
}
