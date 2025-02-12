package com.robustcraft.components;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Component
public class ChatGPTAgent extends AIAgent {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public String sendPrompt(String prompt) {
        // Prepare the payload for OpenAI API
        JSONObject requestPayload = new JSONObject();
        requestPayload.put("model", "gpt-3.5-turbo");
        requestPayload.put("messages", new JSONArray().put(new JSONObject().put("role", "user").put("content", prompt)));

        // Set the headers, including API key
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<String> entity = new HttpEntity<>(requestPayload.toString(), headers);

        // Call the OpenAI API
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

        JSONObject responseJson = new JSONObject(response.getBody());
        return responseJson.getJSONArray("choices").getJSONObject(0).getString("message");
    }
}
