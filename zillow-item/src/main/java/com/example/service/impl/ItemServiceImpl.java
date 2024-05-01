package com.example.service.impl;

import com.example.dao.ItemDao;
import com.example.entity.Item;
import com.example.service.ItemService;
import com.example.vo.ZillowResult;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;

    private static final String BACKEND_B = "backendB";
    // the IP address info that needs to append in blank image routes
    @Value("${zillow.fdfsBasePath.nginx.prefix}")
    private String nginxPrefix;

    @Override
    @CircuitBreaker(name = BACKEND_B)
    @RateLimiter(name = BACKEND_B)
    @Cacheable(cacheNames = "com:example", key = "'getDetails('+#id+')'")
    public Item getItem(String id) {
        Item items = itemDao.findItemById(id);
        ArrayList<String> newImgs = new ArrayList<>();
        for (String img : items.getImgs()) {
            newImgs.add(nginxPrefix + img);
        }
        items.setImgs(newImgs);
        return items;
    }

    @Override
    public ZillowResult addItem(Item item) {
        try {
            itemDao.saveItem(item);
            return ZillowResult.ok("Item added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ZillowResult.error("Failed to add item.");
        }
    }

}
