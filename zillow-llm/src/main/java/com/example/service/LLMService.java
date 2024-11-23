package com.example.service;

import com.example.dao.ItemDao;
import com.example.entity.Item;
import com.example.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LLMService {

    @Autowired
    private ItemDao itemDao;

    public List<Item> getItemsByCity(String city) {
        List<Item> itemList = itemDao.findItemByCity(city);
        return itemList;
    }

}
