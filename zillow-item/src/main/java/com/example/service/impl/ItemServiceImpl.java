package com.example.service.impl;

import com.example.dao.ItemDao;
import com.example.entity.Item;
import com.example.service.ItemService;
import com.example.vo.BaseResult;
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

    @Override
    @CircuitBreaker(name = BACKEND_B)
    @RateLimiter(name = BACKEND_B)
    @Cacheable(cacheNames = "com:example", key = "'getDetails('+#id+')'")
    public Item getItemById(String id) {
        Item items = itemDao.findItemById(id);
        ArrayList<String> newImgs = new ArrayList<>();
        for (String img : items.getImgs()) {
            newImgs.add(img);
        }
        items.setImgs(newImgs);
        return items;
    }

    @Override
    public BaseResult deleteItemById(String id) {
        try {
            itemDao.deleteItemById(id);
            BaseResult ok = new BaseResult();
            ok.setMsg("Item deleted successfully.");
            ok.setStatus(200);
            return ok;
        } catch (Exception e) {
            e.printStackTrace();
            BaseResult error = new BaseResult();
            error.setMsg("Failed to delete item.");
            error.setStatus(500);
            return error;
        }
    }

    @Override
    public BaseResult getItemsByCity(String city, int page, int rows) {
        List<Item> itemList = itemDao.findItemByCity(city, page, rows);
        BaseResult ok = new BaseResult();
        ok.setCnt(itemDao.countItemByCity(city));
        ok.setData(itemList);
        return ok;
    }


    @Override
    public BaseResult addItem(Item item) {
        try {
            itemDao.saveItem(item);
            return BaseResult.ok("Item added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.error("Failed to add item.");
        }
    }

    @Override
    @CacheEvict(cacheNames = "com:example", key = "'getDetails('+#id+')'")
    public BaseResult updateItemStatusById(String id, Boolean isRented, Boolean recommendation) {
        try {
            itemDao.updateItemStatusById(id, isRented, recommendation);
            return BaseResult.ok("Status update successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.error("Status update failed");
        }
    }

    @Override
    @CacheEvict(cacheNames = "com:example", key = "'getDetails('+#id+')'")
    public BaseResult updateItemInfoById(String id, Item item) {
        try {
            itemDao.updateItemInfoById(id, item);
            return BaseResult.ok("Info update successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.error("Info update failed");
        }
    }

    @Override
    @CacheEvict(cacheNames = "com:example", key = "'getDetails('+#id+')'")
    public BaseResult updateItemShowcasesById(String id, ArrayList<String> imgs) {
        try {
            BaseResult ok = BaseResult.ok();
            itemDao.updateItemShowcasesById(id, imgs);
            ok.setMsg("Showcases update successfully.");
            return ok;
        } catch (Exception e) {
            e.printStackTrace();
            BaseResult result = BaseResult.error();
            result.setMsg("Showcases update successfully.");
            return BaseResult.error("Showcases update failed");
        }   
    }

    private List<Item> imgUrlAppend(List<Item> items) {
        for (Item item : items) {
            List<String> newImgs = new ArrayList<>();
            for (String img : item.getImgs()) {
                newImgs.add(img);
            }
            item.setImgs(newImgs);
        }
        return items;
    }

}
