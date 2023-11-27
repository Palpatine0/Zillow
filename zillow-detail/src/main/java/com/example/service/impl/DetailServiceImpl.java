package com.example.service.impl;

import com.example.dao.DetailDao;
import com.example.entity.Item;
import com.example.service.DetailService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DetailServiceImpl implements DetailService {
    @Autowired
    private DetailDao detailDao;

    // the IP address info that needs to append in blank image routes
    @Value("${zillow.banner.nginx.prefix}")
    private String nginxPrefix;

    private static final String BACKEND_B = "backendB";



    @Override
    @CircuitBreaker(name = BACKEND_B)
    @RateLimiter(name = BACKEND_B)
    @Cacheable(cacheNames = "com:example", key = "'getDetails('+#id+')'")
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
