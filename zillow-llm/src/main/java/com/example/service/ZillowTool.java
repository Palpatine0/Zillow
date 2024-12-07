package com.example.service;

import com.example.entity.Comment;
import com.example.entity.Item;
import com.example.entity.Preference;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ZillowTool {

    @Autowired
    private LLMService llmService;

    @Tool
    public List<Item> searchItems(String query) {
        List<Item> items = llmService.searchItems(query);
        return items;
    }

    @Tool
    public List<Comment> searchComments(String query) {
        List<Comment> comments = llmService.searchComments(query);
        return comments;
    }

}
