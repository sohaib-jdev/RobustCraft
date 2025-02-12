//package com.robustcraft.components;
//
//
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.http.*;
//import org.json.JSONObject;
//
//@Component
//public class QwenAgent {
//
//    @Value("${qwen.api.key}")
//    private String apiKey;
//
//    @Value("${qwen.api.url}")
//    private String apiUrl;
//
//    @Value("classpath:/prompts/prompt-template.st")
//    private Resource promptTemplate;
//
//    private RestTemplate restTemplate = new RestTemplate();
//
//    public String sendPrompt(String userPrompt) {
//        // Prepare the payload for Qwen API
//        JSONObject requestPayload = new JSONObject();
//        requestPayload.put("model", "qwen-7b");
//        requestPayload.put("input", createContext(userPrompt));
//
//
//        // Set the headers, including API key
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer " + apiKey);
//
//        HttpEntity<String> entity = new HttpEntity<>(requestPayload.toString(), headers);
//
//        // Call the Qwen API
//        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
//
//        //TODO
//        //change response to return html form as json format
//
//        JSONObject responseJson = new JSONObject(response.getBody());
//        return responseJson.getString("response");
//    }
//
//    protected String createContext(String userPrompt) {
//
//        String chatResponse;
//
//        try {
//            chatResponse = chatClient.prompt().user(u -> {
//                u.text(promptTemplate);
//                // u.param("number_of_question", String.valueOf(message.getNumberOfQuestion()));
//                // u.param("number_of_choice", String.valueOf(message.getNumberOfChoice()));
//                u.param("context", userPrompt);
//                // u.param("prompt_note", message.getPromptNote());
//                // u.param("format", aiResponseFormat);
//            }).call().content();
//        } catch (Exception e) {
//            //log.error("Error while calling AI service: {}", e.getMessage());
//            throw new RuntimeException("Error communicating with AI service", e);
//        }
//        return chatResponse;
//    }
//
//
//
//}
//
