package com.example.service.impl;

import com.example.dao.BuyActionDao;
import com.example.entity.Item;
import com.example.message.ZillowBuyMessage;
import com.example.service.BuyActionService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class BuyActionServiceImpl implements BuyActionService {
    @Autowired
    private BuyActionDao buyActionDao;

    @Autowired
    private StreamBridge streamBridge;

    @Value("${zillow.cache.names.item.prefix}")
    private String itemPrefix;

    @Value("${zillow.cache.names.item.suffix}")
    private String itemSuffix;


    @Override
    public ZillowResult buyAction(String itemId, String userId,String startDate,String endDate,String price) {
        // S1: see if a product is buyable
        String key = itemPrefix + "::" + itemSuffix + "(" + itemId + ")";
        Item item = buyActionDao.getItem(key);
        // feedback if unbuyable
        if (item.getIsRented()) {
            ZillowResult error = ZillowResult.error();
            error.setMsg("Late, it's occupied. ");
            return error;
        }

        // S2: create a message object then send to MQ
        ZillowBuyMessage zillowBuyMessage = new ZillowBuyMessage();
        zillowBuyMessage.setItemId(itemId);
        zillowBuyMessage.setUserId(userId);
        zillowBuyMessage.setStartDate(startDate);
        zillowBuyMessage.setEndDate(endDate);
        zillowBuyMessage.setPrice(price);
        boolean messageResult = streamBridge.send("zillowMessenger-out-0", zillowBuyMessage); // zillowMessenger-in-0: see the yml


        // S3: return a result base on the result from MQ
        if (messageResult) {
            ZillowResult ok = ZillowResult.ok();
            ok.setMsg("Successfully purchased");
            return ok;
        } else {
            ZillowResult error = ZillowResult.error();
            error.setMsg("Server error");
            return error;
        }

    }
}
