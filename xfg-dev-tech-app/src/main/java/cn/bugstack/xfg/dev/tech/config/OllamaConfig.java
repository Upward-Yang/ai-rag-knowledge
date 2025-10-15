package cn.bugstack.xfg.dev.tech.config;

import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.ollama.OllamaEmbeddingClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiEmbeddingClient;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.PgVectorStore;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Auther: yangjian
 * @Date: 2025-10-14 - 10 - 14 - 10:01
 * @Description: cn.bugstack.xfg.dev.tech.config
 * @version: 1.0
 */
@Configuration
public class OllamaConfig {
    @Bean
    public OpenAiApi openAiApi(@Value("${spring.ai.openai.base-url}") String baseUrl
            , @Value("${spring.ai.openai.api-key}") String apikey) {
        return new OpenAiApi(baseUrl, apikey);
    }

    @Bean
    public OllamaChatClient ollamaChatClient(OllamaApi ollamaApi) {
        return new OllamaChatClient(ollamaApi);
    }

    @Bean
    public OpenAiChatClient openAiChatClient(OpenAiApi openAiApi) {
        return new OpenAiChatClient(openAiApi);
    }

    /**
     * 用于切割文本的操作
     * @return
     */
    @Bean
    public TokenTextSplitter tokenTextSplitter() {
        return new TokenTextSplitter();
    }

    /**
     * 把知识库缓存到内存
     * @param ollamaApi
     * @return
     */
    @Bean
    public SimpleVectorStore vectorStore(@Value("${spring.ai.rag.embed}") String model
            , OllamaApi ollamaApi, OpenAiApi openAiApi) {
        if ("nomic-embed-text".equalsIgnoreCase(model)) {
            OllamaEmbeddingClient embeddingClient = new OllamaEmbeddingClient(ollamaApi);
            embeddingClient.withDefaultOptions(OllamaOptions.create().withModel("nomic-embed-text"));
            return new SimpleVectorStore(embeddingClient);
        } else {
            OpenAiEmbeddingClient embeddingClient = new OpenAiEmbeddingClient(openAiApi);
            return new SimpleVectorStore(embeddingClient);
        }
    }

    /**
     * 把知识库通过 JdbcTemplate 也就是 pgsql 操作，存储到向量库
     * @param ollamaApi
     * @param jdbcTemplate
     * @return
     */
        @Bean
        public PgVectorStore pgVectorStore(@Value("${spring.ai.rag.embed}") String model
                , OllamaApi ollamaApi, OpenAiApi openAiApi, JdbcTemplate jdbcTemplate) {
            if ("nomic-embed-text".equalsIgnoreCase(model)) {
                OllamaEmbeddingClient embeddingClient = new OllamaEmbeddingClient(ollamaApi);
                embeddingClient.withDefaultOptions(OllamaOptions.create().withModel("nomic-embed-text"));
                return new PgVectorStore(jdbcTemplate, embeddingClient, 768);
            } else {
                OpenAiEmbeddingClient embeddingClient = new OpenAiEmbeddingClient(openAiApi);
                return new PgVectorStore(jdbcTemplate, embeddingClient,768);
            }
        }
}
