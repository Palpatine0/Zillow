package com.example.service;

import com.example.entity.Comment;
import com.example.entity.Item;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ZillowTool {

    @Autowired
    private ZillowService zillowService;

    @Tool
    public List<Item> searchItems(String query) {
        List<Item> items = zillowService.searchItems(query);
        return items;
    }

    @Tool
    public List<Comment> searchComments(String query) {
        List<Comment> comments = zillowService.searchComments(query);
        return comments;
    }

}
