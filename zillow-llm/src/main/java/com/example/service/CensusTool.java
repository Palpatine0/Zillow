package com.example.service;

import com.example.entity.Comment;
import com.example.entity.Item;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CensusTool {

    @Autowired
    private CensusService censusService;

}
