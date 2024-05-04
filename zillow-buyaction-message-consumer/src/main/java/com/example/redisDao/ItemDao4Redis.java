package com.example.redisDao;

import com.example.entity.Item;

public interface ItemDao4Redis {
    Item getItem(String item_key);

    boolean setItem(String item_key, Object value);
}
