package com.example.controller;


import com.example.entity.Item;
import com.example.entity.Order;
import com.example.service.ItemService;
import com.example.service.OrderServiceFeignClient;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/item")
public class
ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired(required = false)
    private OrderServiceFeignClient orderServiceFeignClient;

    @GetMapping("/getItem")
    public Item getItem(String id) {
        return itemService.getItem(id);
    }

    @GetMapping("/getItemByCity")
    public ZillowResult getItemByCity(String city, int page, @RequestParam(defaultValue = "5") int rows) {
        return itemService.getItemByCity(city, page, rows);
    }

    @GetMapping("/adminGetItemByCity")
    public ZillowResult adminGetItemByCity(String city, int page, @RequestParam(defaultValue = "6") int rows) {
        return itemService.getItemByCity(city, page, rows);
    }


    @PostMapping("/addItem")
    public ZillowResult addItem(String title, Long sales, Boolean recommendation, Byte weight, Long price,
                                String city, String rentType, String houseType, String orientation,
                                String level, String style, String type, String years,
                                @RequestParam(required = false) String buytime,
                                Boolean isRented, String beds, String baths, String area) throws ParseException {
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
        imgs.add("group1/M00/00/00/CgAEDGVd4TGAaUGTABv2R2xYQ3I511.png");
        imgs.add("group1/M00/00/00/CgAEDGVd4TGAaUGTABv2R2xYQ3I511.png");
        imgs.add("group1/M00/00/00/CgAEDGVd4TGAaUGTABv2R2xYQ3I511.png");

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
    public ZillowResult updateItemStatusById(String id, Boolean isRented, Boolean recommendation) {
        return itemService.updateItemStatusById(id, isRented, recommendation);
    }

    @PostMapping("/updateItemInfoById")
    public ZillowResult updateItemInfoById(String id, String title, Long sales, Boolean recommendation, Byte weight, Long price,
                                           String city, String rentType, String houseType, String orientation,
                                           String level, String style, String type, String years,
                                           String img1, String img2, String img3,
                                           @RequestParam(required = false) String buytime,
                                           Boolean isRented, String beds, String baths, String area) throws ParseException {
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
    public ZillowResult updateItemShowcasesById(String id, String img1, String img2, String img3) {
        ArrayList<String> imgs = new ArrayList<>();
        imgs.add(img1);
        imgs.add(img2);
        imgs.add(img3);
        return itemService.updateItemShowcasesById(id, imgs);
    }


}
