package com.robustcraft.components;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

public abstract class AIAgent {

    private ChatClient chatClient;

    @Value("classpath:/prompts/prompt-template.st")
    private Resource promptTemplate;

    public abstract String sendPrompt(String prompt);

    protected String createContext(String userPrompt) {

        String chatResponse;

        try {
            chatResponse = chatClient.prompt().user(u -> {
                u.text(promptTemplate);
               // u.param("number_of_question", String.valueOf(message.getNumberOfQuestion()));
               // u.param("number_of_choice", String.valueOf(message.getNumberOfChoice()));
                u.param("context", userPrompt);
               // u.param("prompt_note", message.getPromptNote());
               // u.param("format", aiResponseFormat);
            }).call().content();
        } catch (Exception e) {
            //log.error("Error while calling AI service: {}", e.getMessage());
            throw new RuntimeException("Error communicating with AI service", e);
        }
        return chatResponse;
    }

    //TODO
    //category, context (2 columns)
    //get context from db (get html form)
    //public abstract getContextFromDb(String userPrompt);



}
