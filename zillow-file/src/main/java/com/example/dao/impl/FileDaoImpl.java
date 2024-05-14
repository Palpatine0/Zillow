package com.example.dao.impl;

import com.example.dao.FileDao;
import com.example.entity.Banner;
import com.example.entity.HouseImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileDaoImpl implements FileDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Banner> findBanners(Query query) {
        List<Banner> banner = mongoTemplate.find(query, Banner.class);
        return banner;
    }

    @Override
    public void saveHouseImage(HouseImage houseImage) {
        mongoTemplate.save(houseImage);
    }


}
