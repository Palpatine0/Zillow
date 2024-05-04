package com.example.service.impl;

import com.example.dao.BuyTimeDao;
import com.example.entity.Item;
import com.example.service.BuyTimeService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyTimeServiceImpl implements BuyTimeService {
    @Autowired
    private BuyTimeDao buyTimeDao;

    @Override
    public ZillowResult getBuyTime(String itemId) {
        Item item = buyTimeDao.getItem(itemId);
        ZillowResult ok = ZillowResult.ok();
        ok.setTime(item.getBuytime().getTime());
        return ok;
    }
}
