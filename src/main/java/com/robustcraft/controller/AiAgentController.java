package com.robustcraft.controller;

import com.robustcraft.service.AiAgentService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/ai")
public class AiAgentController {

    @Autowired
    private AiAgentService aiService;

//    //base 64 file (excel)
//    @PostMapping("/getForm")
//    public String getAIResponse(@RequestBody String prompt) {
//        return aiService.getResponse(prompt);
//    }

    @PostMapping("/getForm")
    public String getAIResponse(@RequestBody String base64FileData) {
        // Extract the file content from the Base64 string
        String[] parts = base64FileData.split(","); // Splitting to handle data URL (e.g., "data:image/png;base64,...")
        String fileData = parts[1]; // Base64 content after the comma
        return aiService.getResponse(fileData);
    }
}
