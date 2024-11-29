package com.example.controller;

import com.example.llm.Agent;
import org.springframework.web.bind.annotation.*;


@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/llm")
public class LLMController {

    private Agent agent;
    public LLMController(Agent agent) {
        this.agent = agent;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("question") String question) {
        return agent.chat(question);
    }
}
