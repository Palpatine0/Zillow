package com.example.config;

import com.example.constant.LLMConstant;
import com.example.llm.Agent;
import com.example.llm.ZillowTool;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.Tokenizer;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.WebSearchContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.web.search.WebSearchEngine;
import dev.langchain4j.web.search.tavily.TavilyWebSearchEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    Agent agent(
        StreamingChatLanguageModel streamingChatLanguageModel,
        ChatLanguageModel chatLanguageModel,
        Tokenizer tokenizer,
        RetrievalAugmentor retrievalAugmentor,
        ZillowTool zillowTool
    ) {
        return AiServices
            .builder(Agent.class)
            .chatLanguageModel(chatLanguageModel)
            .streamingChatLanguageModel(streamingChatLanguageModel)
            .retrievalAugmentor(retrievalAugmentor)
            // Content in session after "LLMConstant.MAX_TOKEN" will be forgotten
            .chatMemoryProvider(chatId -> TokenWindowChatMemory.builder()
                .id(chatId)
                .maxTokens(LLMConstant.MAX_TOKEN, tokenizer)
                .build()
            )
            // .chatMemory(MessageWindowChatMemory.withMaxMessages(LLMConstant.CHAT_MEMORY))
            .tools(zillowTool)
            .build();
    }

    @Bean
    StreamingChatLanguageModel streamingModel() {
        return OpenAiStreamingChatModel
            .builder()
            .modelName(OpenAiChatModelName.GPT_3_5_TURBO)
            .apiKey(LLMConstant.OPENAI_API_KEY)
            .build();
    }

    @Bean
    ChatLanguageModel chatLanguageModel() {
        return OpenAiChatModel
            .builder()
            .modelName(OpenAiChatModelName.GPT_3_5_TURBO)
            .apiKey(LLMConstant.OPENAI_API_KEY)
            .build();
    }

    @Bean
    Tokenizer tokenizer() {
        return new OpenAiTokenizer(OpenAiChatModelName.GPT_3_5_TURBO);
    }

    @Bean
    ContentRetriever webSearchContentRetriever() {
        WebSearchEngine webSearchEngine = TavilyWebSearchEngine.builder()
            .apiKey(LLMConstant.TAVILI_API_KEY)
            .build();

        return WebSearchContentRetriever.builder()
            .webSearchEngine(webSearchEngine)
            .maxResults(3)
            .build();
    }

    @Bean
    RetrievalAugmentor retrievalAugmentor(ContentRetriever webSearchContentRetriever) {
        return DefaultRetrievalAugmentor
            .builder()
            .contentRetriever(webSearchContentRetriever)
            .build();
    }
}