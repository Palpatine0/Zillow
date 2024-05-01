package com.example.service;

import com.example.entity.Item;
import com.example.vo.ZillowResult;

public interface ItemService {
    Item getItem(String id);

    ZillowResult addItem(Item item);
}
