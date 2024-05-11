package com.example.service.impl;

import com.example.dao.BMItemDao;
import com.example.dao.BMOrderDao;
import com.example.entity.Item;
import com.example.entity.Order;
import com.example.redisDao.ItemDao4Redis;
import com.example.service.BuyActionMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BuyActionMessageServiceImpl implements BuyActionMessageService {

    @Autowired
    private BMItemDao BMItemDao;
    @Autowired
    private ItemDao4Redis itemDao4Redis;
    @Autowired
    private BMOrderDao BMOrderDao;

    @Value("${zillow.cache.names.item.prefix}")
    private String itemPrefix;

    @Value("${zillow.cache.names.item.suffix}")
    private String itemSuffix;

    @Override
    public boolean buyAction(String itemId, String phone) {
        //S1: get item from Redis by key (prefix::suffix)
        String key = itemPrefix + "::" + itemSuffix + "(" + itemId + ")";
        Item item = itemDao4Redis.getItem(key);

        //S2: set item as rented
        item.setIsRented(true);
        // update item to Redis
        boolean isRedisUpdate = itemDao4Redis.setItem(key, item);

        //S4: update success: generate order data
        if (isRedisUpdate) {
            // update item to mongodb
            long row = BMItemDao.updateItem(itemId, true);
            if (row == 1) {
                Order order = new Order();
                order.setItemId(itemId);
                order.setPrice(item.getPrice().toString());
                order.setTitle(item.getTitle());
                BMOrderDao.saveOrder(order);
                return true;
            }
        }

        return false;
    }
}
