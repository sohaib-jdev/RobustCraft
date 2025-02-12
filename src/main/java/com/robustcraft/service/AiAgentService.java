package com.robustcraft.service;

import com.robustcraft.components.AIAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AiAgentService {

    //spring profiler
    private AIAgent aiAgent;

    //we can get qualifier from app.props
    @Autowired
    public AiAgentService(@Qualifier("qwenAgent")AIAgent aiAgent) {
        this.aiAgent = aiAgent;
    }

    public String getResponse(String prompt) {
        return aiAgent.sendPrompt(prompt);
    }}
