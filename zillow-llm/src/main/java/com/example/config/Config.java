package com.example.config;

import com.example.constant.LLMConstant;
import com.example.llm.Agent;
import com.example.llm.Tools;
import com.example.service.LLMService;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

@Configuration
public class Config {

    @Bean
    Agent customerSupportAgent(
        StreamingChatLanguageModel streamingChatLanguageModel,
        ChatLanguageModel chatLanguageModel,
        ContentRetriever contentRetriever,
        Tools tools
    ) {
        return AiServices
            .builder(Agent.class)
            .chatLanguageModel(chatLanguageModel)
            .streamingChatLanguageModel(streamingChatLanguageModel)
            .chatMemory(MessageWindowChatMemory.withMaxMessages(LLMConstant.CHAT_MEMORY))
            .tools(tools)
            .contentRetriever(contentRetriever)
            .build();
    }

    @Bean
    Tools customerServiceTools() {
        return new Tools(new LLMService());
    }

    @Bean
    StreamingChatLanguageModel streamingModel() {
        String key = LLMConstant.OPENAI_API_KEY;
        return OpenAiStreamingChatModel.withApiKey(key);
    }

    @Bean
    ChatLanguageModel chatLanguageModel() {
        String key = LLMConstant.OPENAI_API_KEY;
        return OpenAiChatModel.withApiKey(key);
    }

    @Bean
    ContentRetriever contentRetriever(
        EmbeddingStore<TextSegment> embeddingStore, EmbeddingModel embeddingModel) {
        int maxResults = 1;
        double minScore = 0.6;

        return EmbeddingStoreContentRetriever
            .builder()
            .embeddingStore(embeddingStore)
            .embeddingModel(embeddingModel)
            .maxResults(maxResults)
            .minScore(minScore)
            .build();
    }

    @Bean
    EmbeddingModel embeddingModel() {
        return new AllMiniLmL6V2EmbeddingModel();
    }

    @Bean
    EmbeddingStore<TextSegment> embeddingStore(EmbeddingModel embeddingModel, ResourceLoader resourceLoader) throws IOException {
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        return embeddingStore;
    }

    @Bean
    FileSystemResourceLoader resourceLoader() {
        return new FileSystemResourceLoader();
    }

}
