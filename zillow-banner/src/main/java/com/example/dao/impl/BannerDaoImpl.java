package com.example.dao.impl;

import com.example.dao.BannerDao;
import com.example.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BannerDaoImpl implements BannerDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<Banner> selectBanner(Query query) {
        List<Banner> banner = mongoTemplate.find(query, Banner.class);
        return banner;
    }
}
