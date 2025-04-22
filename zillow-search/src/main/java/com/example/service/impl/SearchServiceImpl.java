package com.example.service.impl;

import com.example.dao.SearchDao;
import com.example.entity.Item;
import com.example.entity.Item4ES;
import com.example.service.SearchService;
import com.example.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SearchDao searchDao;

    @Override
    public BaseResult searchByCity(String city, int page, int rows) {
        List<Item4ES> item4ESList = searchDao.searchByCity(city, page, rows);
        return BaseResult.ok(item4ESList);
    }

    @Override
    public BaseResult searchByKeyWord(String city, String content, int page, int rows) {
        List<Item4ES> item4ESList = searchDao.searchByKeyWord(city, content, page, rows);
        return BaseResult.ok(item4ESList);
    }

    @Override
    public void esinit() {
        List<Item> itemList = mongoTemplate.findAll(Item.class);
        ArrayList<Item4ES> arrayList = new ArrayList<>();
        for (Item item : itemList) {
            Item4ES item4ES = new Item4ES();
            item4ES.setId(item.getId());
            item4ES.setTitle(item.getTitle());
            item4ES.setCity(item.getCity());
            item4ES.setAptType(item.getHouseType());
            item4ES.setImgs(item.getImgs().get(0));
            item4ES.setPrice(String.valueOf(item.getPrice()));
            item4ES.setRentType(item.getRentType());
            arrayList.add(item4ES);
        }
        searchDao.batchInsertToES(arrayList);
    }


}
