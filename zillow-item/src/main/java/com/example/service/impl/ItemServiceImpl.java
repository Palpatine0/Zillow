package com.example.service.impl;

import com.example.dao.ItemDao;
import com.example.entity.Item;
import com.example.service.ItemService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Value("${zillow.banner.nginx.prefix}")
    private String nginxPrefix;


    @Override
    public ZillowResult getItem(String city) {
        // 1: get data
        Query query = new Query();
        Criteria criteria = new Criteria();
        // set cri: make sure data received will be data in cur city
        criteria.andOperator(Criteria.where("city").is(city));
        query.addCriteria(criteria);

        // S2: pagination
        query.with(PageRequest.of(0, 4));
        List<Item> items = itemDao.selectItem(query);
        int sz = items.size();
        if (sz < 4) {
            int lack = 4 - sz;
            Query queryy = new Query();
            Criteria criteriaa = new Criteria();
            criteriaa.andOperator(Criteria.where("city").is(city));
            queryy.addCriteria(criteriaa);
            queryy.with(PageRequest.of(0, lack));
            List<Item> itemm = itemDao.selectItem(queryy);

            items.addAll(itemm);
        }


        //S3: append url for image
        items = this.imgUrlAppend(items);
        return ZillowResult.ok(items);
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
