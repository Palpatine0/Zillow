package com.example.service.impl;

import com.example.dao.RecommendationDao;
import com.example.entity.Item;
import com.example.service.RecommendationService;
import com.example.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private RecommendationDao recommendationDao;

    @Override
    public BaseResult getRecommendations(String city) {
        // 1: get data
        Query query = new Query();
        Criteria criteria = new Criteria();
        // set cri: make sure data received will be data in cur city
        criteria.andOperator(
                Criteria.where("city").is(city),
                Criteria.where("recommendation").is(true)
        );
        query.addCriteria(criteria);

        // S2: pagination
        query.with(PageRequest.of(0, 4));
        List<Item> items = recommendationDao.findRecommendations(query);
        int sz = items.size();
        if (sz < 4) {
            int lack = 4 - sz;
            Query queryy = new Query();
            Criteria criteriaa = new Criteria();
            criteriaa.andOperator(
                    Criteria.where("city").is(city),
                    Criteria.where("recommendation").is(true)
            );
            queryy.addCriteria(criteriaa);
            queryy.with(PageRequest.of(0, lack));
            List<Item> itemm = recommendationDao.findRecommendations(queryy);

            items.addAll(itemm);
        }

        // fallback option, really have nothing to show, fill the remaining will all this
        if(items.size() < 4){
            for (int i = items.size(); i < 4; i++) {
                items.add(fallbackItem());
            }
        }

        //S3: append url for image
        items = this.imgUrlAppend(items);
        return BaseResult.ok(items);
    }

    private List<Item> imgUrlAppend(List<Item> items){
        for(Item item : items){
            List<String> newImgs = new ArrayList<>();
            for(String img : item.getImgs()){
                newImgs.add(img);
            }
            item.setImgs(newImgs);
        }
        return items;
    }

    // really have nothing to show, fill the remaining will all these
    private Item fallbackItem(){
        Item item = new Item();
        item.setId("5ec1ec6b7f56a946fb7fdffa");
        item.setCity("B");
        item.setHouseType("150 ㎡");
        ;
        item.setImgs(
                Arrays.asList(
                        "group1/M00/00/00/CgAEDGVQUviADlg-AAccr9iz7Ew746.png",
                        "group1/M00/00/00/CgAEDGVQUrWANCPLABMCpJXCu_8377.png",
                        "group1/M00/00/00/CgAEDGVQUrWANCPLABMCpJXCu_8377.png"
                ));
        item.setPrice(12000L);
        item.setRecommendation(true);
        item.setWeight((byte) 9);
        ;
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
