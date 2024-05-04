package com.example.service;

import com.example.entity.Item;
import com.example.vo.ZillowResult;

import java.util.ArrayList;

public interface ItemService {
    Item getItem(String id);

    ZillowResult getItemByCity(String city, int page, int rows);

    ZillowResult addItem(Item item);

    ZillowResult updateItemStatusById(String id, Boolean isRented, Boolean recommendation);
    ZillowResult updateItemInfoById(String id, Item item);

    ZillowResult updateItemShowcasesById(String id, ArrayList<String> imgs);

}
