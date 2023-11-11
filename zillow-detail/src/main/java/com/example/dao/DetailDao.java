package com.example.dao;


import com.example.entity.Banner;
import com.example.entity.Item;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface DetailDao {
    Item findItemById(String id);
}
