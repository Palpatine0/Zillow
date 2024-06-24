package com.example.service.impl;

import com.example.dao.BuyTimeDao;
import com.example.entity.Item;
import com.example.service.BuyTimeService;
import com.example.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyTimeServiceImpl implements BuyTimeService {
    @Autowired
    private BuyTimeDao buyTimeDao;

    @Override
    public BaseResult getBuyTime(String itemId) {
        Item item = buyTimeDao.getItem(itemId);
        BaseResult ok = BaseResult.ok();
        ok.setTime(item.getBuytime().getTime());
        return ok;
    }
}
