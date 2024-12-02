package com.example.controller;

import com.example.llm.ClientChatbotAgent;
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


    @GetMapping(value = "/chat", produces = "text/event-stream")
    public Flux<String> chat(String chatId, String question) {
        return Flux.fromArray(clientChatbotAgent.chat(chatId, question).split("(?<=\\s)")).delayElements(Duration.ofMillis(20));
    }

    @GetMapping(value = "/chatStream", produces = "text/event-stream")
    public Flux<String> chatStream(String chatId, String question) {
        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();
        clientChatbotAgent.chatStream(chatId, question)
            .onNext(sink::tryEmitNext)
            .onComplete(c -> sink.tryEmitComplete())
            .onError(sink::tryEmitError)
            .start();
        return sink.asFlux();
    }

}