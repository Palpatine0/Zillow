package com.example.service;

import com.example.dao.ItemDao;
import com.example.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LLMService {

    @Autowired
    private ItemDao itemDao;

    public List<Item> getItemsByCity(String city) {
        List<Item> itemList = itemDao.findItemsByCity(city);
        return itemList;
    }

    public List<Item> getItems() {
        List<Item> itemList = itemDao.findItems();
        return itemList;
    }

}
