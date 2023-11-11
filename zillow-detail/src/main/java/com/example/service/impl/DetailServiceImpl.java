package com.example.service.impl;

import com.example.dao.DetailDao;
import com.example.entity.Banner;
import com.example.entity.Item;
import com.example.service.DetailService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailServiceImpl implements DetailService {
    @Autowired
    private DetailDao detailDao;

    // the IP address info that needs to append in blank image routes
    @Value("${zillow.banner.nginx.prefix}")
    private String nginxPrefix;


    @Override
    @Cacheable(cacheNames = "com:zillow:details", key = "'getDetails('+#id+')'")
    public Item getDetail(String id) {
        Item items = detailDao.findItemById(id);

        ArrayList<String> newImgs = new ArrayList<>();
        for (String img : items.getImgs()) {
            newImgs.add(nginxPrefix+img);
        }
        items.setImgs(newImgs);

        return items;
    }
}
