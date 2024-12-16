package com.example.config;

import com.example.constant.LLMConstant;
import com.example.llm.ClientChatbotAgent;
import com.example.llm.DataSummaryAgent;
import com.example.llm.UserPreferenceAgent;
import com.example.service.ZillowTool;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.Tokenizer;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.content.retriever.WebSearchContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.web.search.WebSearchEngine;
import dev.langchain4j.web.search.tavily.TavilyWebSearchEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResourceLoader;

@Configuration
public class LLMConfiguration {

    // Agents
    @Bean
    ClientChatbotAgent clientChatbotAgent(
        ChatLanguageModel chatLanguageModel,
        Tokenizer tokenizer,
        RetrievalAugmentor retrievalAugmentor,
        ZillowTool zillowTool
    ) {
        return AiServices
            .builder(ClientChatbotAgent.class)
            .chatLanguageModel(chatLanguageModel)
            .retrievalAugmentor(retrievalAugmentor)
            .chatMemoryProvider(chatId -> TokenWindowChatMemory.builder()
                .id(chatId)
                .maxTokens(LLMConstant.MAX_TOKEN, tokenizer)
                .build()
            )
            .tools(zillowTool)
            .build();
    }

    @Bean
    UserPreferenceAgent userPreferenceAgent(
        ChatLanguageModel chatLanguageModel,
        Tokenizer tokenizer
    ) {
        return AiServices
            .builder(UserPreferenceAgent.class)
            .chatLanguageModel(chatLanguageModel)
            .chatMemoryProvider(chatId -> TokenWindowChatMemory.builder()
                .id(chatId)
                .maxTokens(LLMConstant.MAX_TOKEN, tokenizer)
                .build()
            )
            .build();
    }

    @Bean
    DataSummaryAgent summaryAgent(
        ChatLanguageModel chatLanguageModel,
        Tokenizer tokenizer
    ) {
        return AiServices
            .builder(DataSummaryAgent.class)
            .chatLanguageModel(chatLanguageModel)
            .chatMemoryProvider(chatId -> TokenWindowChatMemory.builder()
                .id(chatId)
                .maxTokens(LLMConstant.MAX_TOKEN, tokenizer)
                .build()
            )
            .build();
    }


    // Models
    @Bean
    ChatLanguageModel chatLanguageModel() {
        return OpenAiChatModel
            .builder()
            .modelName(OpenAiChatModelName.GPT_3_5_TURBO)
            .apiKey(LLMConstant.OPENAI_API_KEY)
            .build();
    }
    @Bean
    EmbeddingModel embeddingModel() {
        return new AllMiniLmL6V2EmbeddingModel();
    }

    @Bean
    ContentRetriever contentRetriever(
        EmbeddingModel embeddingModel,
        EmbeddingStore<TextSegment> embeddingStore
    ) {
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
    EmbeddingStore<TextSegment> embeddingStore(){
        return new InMemoryEmbeddingStore<>();
    }

    @Bean
    FileSystemResourceLoader resourceLoader() {
        return new FileSystemResourceLoader();
    }

    // Web search
    @Bean
    ContentRetriever webSearchContentRetriever() {
        WebSearchEngine webSearchEngine = TavilyWebSearchEngine.builder()
            .apiKey(LLMConstant.TAVILI_API_KEY)
            .build();

        return WebSearchContentRetriever
            .builder()
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