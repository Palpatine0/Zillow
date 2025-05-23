package com.example.controller;


import com.example.entity.Item;
import com.example.entity.Order;
import com.example.service.ItemService;
import com.example.service.OrderServiceFeignClient;
import com.example.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired(required = false)
    private OrderServiceFeignClient orderServiceFeignClient;

    @GetMapping("/getItemById")
    public Item getItemById(String id) {
        return itemService.getItemById(id);
    }

    @DeleteMapping("/deleteItemById")
    public BaseResult deleteItemById(String id) {
        return itemService.deleteItemById(id);
    }

    @GetMapping("/getItemsByCity")
    public BaseResult getItemsByCity(String city, int page, @RequestParam(defaultValue = "5") int rows) {
        return itemService.getItemsByCity(city, page, rows);
    }


    @PostMapping("/addItem")
    public BaseResult addItem(String title, Long sales, Boolean recommendation, Byte weight, Long price, String city, String rentType, String houseType, String orientation, String level, String style, String type, String years, String img1, String img2, String img3, @RequestParam(required = false) String buytime, Boolean isRented, String beds, String baths, String area) throws ParseException {
        Map<String, String> info = new HashMap<>();
        info.put("orientation", orientation);
        info.put("level", level);
        info.put("style", style);
        info.put("type", type);
        info.put("years", years);
        info.put("beds", beds);
        info.put("baths", baths);
        info.put("area", area);


        ArrayList<String> imgs = new ArrayList<>();
        imgs.add(img1);
        imgs.add(img2);
        imgs.add(img3);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;

        if (buytime == null || buytime.isEmpty()) {
            date = new Date();
        } else {
            date = formatter.parse(buytime);
        }

        Item item = new Item();
        item.setTitle(title);
        item.setSales(sales);
        item.setRecommendation(recommendation);
        item.setWeight(weight);
        item.setPrice(price);
        item.setCity(city);
        item.setRentType(rentType);
        item.setHouseType(houseType);
        item.setInfo(info);
        item.setImgs(imgs);
        item.setBuytime(date);
        item.setIsRented(isRented);


        return itemService.addItem(item);
    }


    @GetMapping("/getOrder")
    public List<Order> selectOrder(String phone) {
        return orderServiceFeignClient.getOrder(phone);
    }

    @PostMapping("/updateItemStatusById")
    public BaseResult updateItemStatusById(String id, Boolean isRented, Boolean recommendation) {
        return itemService.updateItemStatusById(id, isRented, recommendation);
    }

    @PostMapping("/updateItemInfoById")
    public BaseResult updateItemInfoById(String id, String title, Long sales, Boolean recommendation, Byte weight, Long price, String city, String rentType, String houseType, String orientation, String level, String style, String type, String years, String img1, String img2, String img3, @RequestParam(required = false) String buytime, Boolean isRented, String beds, String baths, String area) throws ParseException {
        Map<String, String> info = new HashMap<>();
        info.put("orientation", orientation);
        info.put("level", level);
        info.put("style", style);
        info.put("type", type);
        info.put("years", years);
        info.put("beds", beds);
        info.put("baths", baths);
        info.put("area", area);


        ArrayList<String> imgs = new ArrayList<>();
        imgs.add(img1);
        imgs.add(img2);
        imgs.add(img3);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;

        if (buytime == null || buytime.isEmpty()) {
            date = new Date();
        } else {
            date = formatter.parse(buytime);
        }

        Item item = new Item();
        item.setTitle(title);
        item.setSales(sales);
        item.setRecommendation(recommendation);
        item.setWeight(weight);
        item.setPrice(price);
        item.setCity(city);
        item.setRentType(rentType);
        item.setHouseType(houseType);
        item.setInfo(info);
        item.setImgs(imgs);
        item.setBuytime(date);
        item.setIsRented(isRented);

        return itemService.updateItemInfoById(id, item);
    }

    @PostMapping("/updateItemShowcasesById")
    public BaseResult updateItemShowcasesById(String id, String img1, String img2, String img3) {
        ArrayList<String> imgs = new ArrayList<>();
        imgs.add(img1);
        imgs.add(img2);
        imgs.add(img3);
        return itemService.updateItemShowcasesById(id, imgs);
    }


}
