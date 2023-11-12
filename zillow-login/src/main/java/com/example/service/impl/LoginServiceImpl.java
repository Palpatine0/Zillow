package com.example.service.impl;

import com.example.dao.LoginDao;
import com.example.entity.Item;
import com.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;

    // the IP address info that needs to append in blank image routes
    @Value("${zillow.banner.nginx.prefix}")
    private String nginxPrefix;


    @Override
    @Cacheable(cacheNames = "com:example", key = "'getDetail('+#id+')'")
    public Item getDetail(String id) {
        Item items = loginDao.findItemById(id);

        ArrayList<String> newImgs = new ArrayList<>();
        for (String img : items.getImgs()) {
            newImgs.add(nginxPrefix+img);
        }
        items.setImgs(newImgs);

        return items;
    }
}
