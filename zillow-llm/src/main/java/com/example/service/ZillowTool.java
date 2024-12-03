package com.example.service;

import com.example.entity.Comment;
import com.example.entity.Item;
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
        // System.out.println("[" + LocalDateTime.now() + "] Entering searchItems with query: " + query);
        List<Item> items = llmService.searchItems(query);
        // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // System.out.println(items);
        // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // System.out.println("[" + LocalDateTime.now() + "] Exiting searchItems");
        return items;
    }

    @Tool
    public List<Comment> searchComments(String query) {
        // System.out.println("[" + LocalDateTime.now() + "] Entering searchComments with query: " + query);
        List<Comment> comments = llmService.searchComments(query);
        // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // System.out.println(comments);
        // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // System.out.println("[" + LocalDateTime.now() + "] Exiting searchComments");
        return comments;
    }
}
