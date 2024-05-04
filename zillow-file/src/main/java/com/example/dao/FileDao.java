package com.example.dao;


import com.example.entity.Banner;
import com.example.entity.HouseImage;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface FileDao {
    List<Banner> selectBanner(Query query);

    void saveHouseImage(HouseImage houseImage);

}
