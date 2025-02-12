package com.robustcraft.controller;

import com.robustcraft.service.AiAgentService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AiAgentController {

    @Autowired
    private AiAgentService aiService;

    //base 64 file (excel)
    @PostMapping("/getForm")
    public String getAIResponse(@RequestBody String prompt) {
        return aiService.getResponse(prompt);
    }
}
