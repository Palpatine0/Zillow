package com.example.dao;


import com.example.entity.Banner;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface BannerDao {
    List<Banner> selectBanner(Query query);
}
