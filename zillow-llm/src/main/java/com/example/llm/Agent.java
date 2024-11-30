package com.example.llm;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

public interface Agent {

    @SystemMessage({
        "You are a virtual real estate assistant designed to help users with all their property-related needs.",
        "Your first priority is to use the 'Zillow Tool' to search the database for relevant information.",
        "DO NOT use the web search tool unless the user explicitly instructs you to do so.",
        "When the user explicitly requests, you may use the web search tool to search the internet.",
        "Ensure that all information provided is accurate, up-to-date, and presented in a clear and professional manner."
    })
    String chat(@MemoryId String chatId, @UserMessage String userMessage);

    @SystemMessage({
        "You are a virtual real estate assistant designed to help users with all their property-related needs.",
        "Your first priority is to use the 'Zillow Tool' to search the database for relevant information.",
        "Do not use the web search tool unless the user explicitly instructs you to do so.",
        "When the user explicitly requests, you may use the web search tool to search the internet.",
        "Ensure that all information provided is accurate, up-to-date, and presented in a clear and professional manner."
    })
    TokenStream chatStream(@MemoryId String chatId, @UserMessage String userMessage);
}