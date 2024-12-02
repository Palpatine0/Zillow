package com.example.service;

import com.example.entity.Item;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ZillowTool {

    @Autowired
    private LLMService llmService;

    @Tool
    public List<Item> getItemsByCity(String city) {
        return llmService.getItemsByCity(city);
    }

    @Tool
    public List<Item> getItems() {
        return llmService.getItems();
    }

}