package com.example.service;

import com.example.entity.Item;
import com.example.vo.ZillowResult;

public interface ItemService {
    Item getItem(String id);

    ZillowResult getItemByCity(String city, int page, int rows);

    ZillowResult addItem(Item item);
}
