package com.example.controller;

import com.example.llm.ClientChatbotAgent;
import com.example.llm.UserPreferenceAgent;
import com.example.service.LLMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;


@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/llm")
public class LLMController {

    @Autowired
    private ClientChatbotAgent clientChatbotAgent;

    @Autowired
    private LLMService llmService;

    @GetMapping("/chat")
    public String chat(String userId, String chatId, String query) {
        llmService.managePreferences(userId, query);
        String answer = clientChatbotAgent.chat(chatId, query);
        return answer;
    }

}
