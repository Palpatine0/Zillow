package com.example.dao.impl;

import com.example.dao.BuyActionDao;
import com.example.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BuyActionDaoImpl implements BuyActionDao {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Item getItem(String key_itemId) {
        Item item = (Item) redisTemplate.opsForValue().get(key_itemId);
        return item;
    }


}
