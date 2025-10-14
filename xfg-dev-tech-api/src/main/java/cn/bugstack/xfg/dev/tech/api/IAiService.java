package cn.bugstack.xfg.dev.tech.api;

import org.springframework.ai.chat.ChatResponse;
import reactor.core.publisher.Flux;

/**
 * @Auther: yangjian
 * @Date: 2025-10-13 - 10 - 13 - 16:37
 * @Description: cn.bugstack.xfg.dev.tech.api
 * @version: 1.0
 */
public interface IAiService {
    ChatResponse generate(String model, String message);

    Flux<ChatResponse> generateStream(String model, String message);

    Flux<ChatResponse> generateStreamRag(String model, String ragTag, String message);
}
