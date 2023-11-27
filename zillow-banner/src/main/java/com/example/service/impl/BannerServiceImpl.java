package com.example.service.impl;

import com.example.dao.BannerDao;
import com.example.entity.Banner;
import com.example.service.BannerService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    // the IP address info that needs to append in blank image routes
    @Value("${zillow.banner.nginx.prefix}")
    private String nginxPrefix;
    @Override
    public ZillowResult getBanner() {
        ZillowResult result = new ZillowResult();
        try {
            // S1: get data
            Query query = new Query();
            query.with(PageRequest.of(0, 6));
            List<Banner> banners = bannerDao.selectBanner(query);

            // S2: append url to data's front, since it hasn't.and put them to a list
            ArrayList<Object> imgRes = new ArrayList<>();
            for (Banner banner : banners) {
                imgRes.add(nginxPrefix+banner.getUrl());
            }

            // S3: feedback
            result.setResults(imgRes);
            result.setStatus(200);

        }catch (Exception e) {
            // failure handling
            e.printStackTrace();
            result.setStatus(500);
            result.setMsg("Failed to get banner");
        }
        return result;
    }
}
