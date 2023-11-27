package com.example.listener;

import com.example.message.ZillowBuyMessage;
import com.example.service.BuyActionMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class BuyActionMessageConsumer {

    @Autowired
    private BuyActionMessageService buyActionMessageService;

    @Bean
    public Consumer<ZillowBuyMessage> zillowMessenger() {
        return message -> {
            String itemId = message.getItemId();
            String username = message.getUsername();
            boolean result = buyActionMessageService.buyAction(itemId, username);
            log.info("message consumer res: " + result);
        };
    }
}
