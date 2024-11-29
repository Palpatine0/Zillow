package com.example.service;

import com.example.constant.LLMConstant;
import com.example.dao.ItemDao;
import com.example.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
