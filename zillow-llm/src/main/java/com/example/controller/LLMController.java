package com.example.controller;

import com.example.llm.ClientChatbotAgent;
import com.example.service.CensusService;
import com.example.service.ZillowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/llm")
public class LLMController {

    @Autowired
    private ClientChatbotAgent clientChatbotAgent;

    @Autowired
    private ZillowService zillowService;

    @Autowired
    private CensusService censusService;

    // Chat Bot
    @GetMapping("/chat")
    public String chat(String userId, String chatId, String query) {
        zillowService.managePreferences(userId, query);
        String answer = clientChatbotAgent.chat(chatId, query);
        return answer;
    }

    // Admin stat board
    @GetMapping("/getNewConstructionStatValues")
    public List<Double> getNewConstructionStatValues() {
        return censusService.getNewConstructionStatValues();
    }

    @GetMapping("/getNewConstructionStatSummary")
    public String getNewConstructionStatSummary() {
        return censusService.getNewConstructionStatSummary();
    }

    @GetMapping("/getHousingVacanciesStatSummary")
    public String getHousingVacanciesStatSummary() {
        return censusService.getHousingVacanciesStatSummary();
    }

    @GetMapping("/getHomeownershipStatSummary")
    public String getHomeownershipStatSummary() {
        return censusService.getHomeownershipStatSummary();
    }

    @GetMapping("/getEstimatedRentedUnitsStatSummary")
    public String getEstimatedRentedUnitsStatSummary() {
        return censusService.getEstimatedRentedUnitsStatSummary();
    }

}
