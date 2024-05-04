package com.example.redisDao.impl;

import com.example.entity.Item;
import com.example.redisDao.ItemDao4Redis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDao4RedisImpl implements ItemDao4Redis {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Item getItem(String item_key) {
        return (Item) redisTemplate.opsForValue().get(item_key);
    }


    // require available check before update
    @Override
    public boolean setItem(String item_key, Object value) {
        // atomic operations
        try {
            redisTemplate.setEnableTransactionSupport(true);
            List<Object> result = redisTemplate.execute(
                    new SessionCallback<List<Object>>() {
                        public List<Object> execute(RedisOperations redisOperations) throws DataAccessException {
                            Item item = (Item) redisOperations.opsForValue().get(item_key);
                            // intercept before updating
                            if (item.getIsRented()) {
                                return null;
                            }
                            redisOperations.multi();
                            redisOperations.opsForValue().set(item_key, value);
                            return redisOperations.exec();
                        }
                    }
            );
            redisTemplate.setEnableTransactionSupport(false);
            // atomic operations failed, return null
            if (null == result) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
