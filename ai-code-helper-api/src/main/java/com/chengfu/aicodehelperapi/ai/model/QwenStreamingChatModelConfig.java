package com.chengfu.aicodehelperapi.ai.model;

import dev.langchain4j.community.model.dashscope.QwenStreamingChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "langchain4j.community.dashscope.streaming-chat-model")
@Data
public class QwenStreamingChatModelConfig {

    private String modelName;
    private String apiKey;
    private boolean stream = true;

    @Resource
    private ChatModelListener chatModelListener;

    @Bean
    public StreamingChatModel qwenStreamingChatModel() {
        return QwenStreamingChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .stream(stream)
                .listeners(List.of(chatModelListener))
                .build();
    }
}