package com.example.service;

import com.example.entity.Item;
import com.example.vo.BaseResult;

import java.util.ArrayList;

public interface ItemService {
    Item getItemById(String id);

    BaseResult deleteItemById(String id);

    BaseResult getItemsByCity(String city, int page, int rows);

    BaseResult addItem(Item item);

    BaseResult updateItemStatusById(String id, Boolean isRented, Boolean recommendation);

    BaseResult updateItemInfoById(String id, Item item);

    BaseResult updateItemShowcasesById(String id, ArrayList<String> imgs);

}
