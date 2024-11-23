package com.example.llm;

import com.example.entity.Item;
import com.example.service.LLMService;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Tools {

    private final LLMService llmService;

    public Tools(LLMService llmService) {
        this.llmService = llmService;
    }

    @Tool
    public List<Item> getItemsByCity(String city) {
        return llmService.getItemsByCity(city);
    }

}
