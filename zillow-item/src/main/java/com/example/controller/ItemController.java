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
public class ItemController {
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


    /*@PostMapping("/addItem")
    public ZillowResult addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }*/

    @PostMapping("/addItem")
    public ZillowResult addItem(@RequestParam String title, @RequestParam Long sales,
                                @RequestParam Boolean recommendation, @RequestParam Byte weight, @RequestParam Long price,
                                @RequestParam String city, @RequestParam String rentType, @RequestParam String houseType,
                                @RequestParam String orientation, @RequestParam String level, @RequestParam String style,
                                @RequestParam String type, @RequestParam String years,
                                @RequestParam(required = false) String buytime,
                                @RequestParam Boolean isRented) throws ParseException {
        Map<String, String> info = new HashMap<>();
        info.put("orientation", orientation);
        info.put("level", level);
        info.put("style", style);
        info.put("type", type);
        info.put("years", years);

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


}
