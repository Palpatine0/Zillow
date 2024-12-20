package com.example.service.impl;

import com.example.dao.TrendyDao;
import com.example.entity.Item;
import com.example.service.TrendyService;
import com.example.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrendyServiceImpl implements TrendyService {

    @Autowired
    private TrendyDao trendyDao;

    @Override
    public BaseResult getTrendies(String city) {
        // S1: get data
        Query query = new Query();
        // set cri: current city data
        query.addCriteria(Criteria.where("city").is(city).and("weight").gt(5));

        // S2: pagination
        query.with(PageRequest.of(0, 4));
        List<Item> items = trendyDao.findTrendies(query);
        for (Item item : items) {
            System.out.println(item);
        }

        // only if current trendy items is less than 4. grab from other city
        int sz = items.size();
        System.out.println("available trendies size: " + sz);
        if (sz < 4) {
            int lack = 4 - sz;
            Query queryy = new Query();
            queryy.addCriteria(Criteria.where("city").is(city));
            queryy.with(PageRequest.of(0, lack));
            List<Item> itemm = trendyDao.findTrendies(queryy);
            items.addAll(itemm);
        }


        // fallback option, if really have nothing to show, fill the remaining will all this
        if (items.size() < 4) {
            for (int i = items.size(); i < 4; i++) {
                items.add(fallbackItem());
            }
        }

        //S3: append url for image
        items = this.imgUrlAppend(items);

        return BaseResult.ok(items);
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


    private Item fallbackItem() {
        Item item = new Item();
        item.setId("5ec1ec6b7f56a946fb7fdffa");
        item.setCity("B");
        item.setHouseType(("150 ㎡"));
        item.setImgs(
                Arrays.asList(
                        "group1/M00/00/00/CgAEDGVQUviADlg-AAccr9iz7Ew746.png",
                        "group1/M00/00/00/CgAEDGVQUviADlg-AAccr9iz7Ew746.png",
                        "group1/M00/00/00/CgAEDGVQUrWANCPLABMCpJXCu_8377.png"
                ));
        item.setPrice(12000L);
        item.setRecommendation(true);
        item.setWeight(((byte) 9));
        item.setRentType("Whole Rentle");
        item.setSales(100L);
        item.setTitle("BVB");
        Map<String, String> info = new HashMap<>();
        info.put("years", "2010");
        info.put("type", "3 bedroom,2 living room");
        info.put("level", "10/18");
        info.put("style", "elaborate");
        info.put("orientation", "N&S");
        item.setInfo(info);
        return item;
    }

}
