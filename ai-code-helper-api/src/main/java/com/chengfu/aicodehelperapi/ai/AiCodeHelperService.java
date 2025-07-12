package com.chengfu.aicodehelperapi.ai;

import com.chengfu.aicodehelperapi.ai.guardrail.SafeInputGuardrail;
import dev.langchain4j.service.*;
import dev.langchain4j.service.guardrail.InputGuardrails;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

import java.util.List;
/**
 * @BelongsProject: ai-code-helper-api
 * @BelongsPackage: com.chengfu.aicodehelperapi.ai
 * @Author: Chengfu Shi
 * @CreateTime: 2025-07-12 09:41
 * @Description: TODO
 * @Version: 1.0
 **/
@InputGuardrails({SafeInputGuardrail.class})
public interface AiCodeHelperService {

    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(String userMessage);

    @SystemMessage(fromResource = "system-prompt.txt")
    Report chatForReport(String userMessage);

    // 学习报告
    record Report(String name, List<String> suggestionList) {
    }

    @SystemMessage(fromResource = "system-prompt.txt")
    Result<String> chatWithRag(String userMessage);

    // 流式对话
    Flux<String> chatStream(@MemoryId int memoryId, @UserMessage String userMessage);
}
