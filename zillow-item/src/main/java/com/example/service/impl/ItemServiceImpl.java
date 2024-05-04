package com.example.service.impl;

import com.example.dao.ItemDao;
import com.example.entity.Item;
import com.example.service.ItemService;
import com.example.vo.ZillowResult;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Item getItemByID(String id) {
        Item items = itemDao.findItemById(id);
        ArrayList<String> newImgs = new ArrayList<>();
        for (String img : items.getImgs()) {
            newImgs.add(img);
        }
        items.setImgs(newImgs);
        return items;
    }

    @Override
    public ZillowResult getItemsByCity(String city, int page, int rows) {
        List<Item> itemList = itemDao.findItemByCity(city, page, rows);
        ZillowResult ok = new ZillowResult();
        ok.setCnt(itemDao.countItemByCity(city));
        ok.setData(itemList);
        return ok;
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

    @Override
    @CacheEvict(cacheNames = "com:example", key = "'getDetails('+#id+')'")
    public ZillowResult updateItemStatusById(String id, Boolean isRented, Boolean recommendation) {
        try {
            itemDao.updateItemStatusById(id, isRented, recommendation);
            return ZillowResult.ok("Status update successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ZillowResult.error("Status update failed");
        }
    }

    @Override
    @CacheEvict(cacheNames = "com:example", key = "'getDetails('+#id+')'")
    public ZillowResult updateItemInfoById(String id, Item item) {
        try {
            itemDao.updateItemInfoById(id, item);
            return ZillowResult.ok("Info update successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ZillowResult.error("Info update failed");
        }
    }

    @Override
    @CacheEvict(cacheNames = "com:example", key = "'getDetails('+#id+')'")
    public ZillowResult updateItemShowcasesById(String id, ArrayList<String> imgs) {
        try {
            ZillowResult ok = ZillowResult.ok();
            itemDao.updateItemShowcasesById(id, imgs);
            ok.setMsg("Showcases update successfully.");
            return ok;
        } catch (Exception e) {
            e.printStackTrace();
            ZillowResult result = ZillowResult.error();
            result.setMsg("Showcases update successfully.");
            return ZillowResult.error("Showcases update failed");
        }
    }

    private List<Item> imgUrlAppend(List<Item> items) {
        for (Item item : items) {
            List<String> newImgs = new ArrayList<>();
            for (String img : item.getImgs()) {
                newImgs.add(nginxPrefix + img);
            }
            item.setImgs(newImgs);
        }
        return items;
    }

}
