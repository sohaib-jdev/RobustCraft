package com.robustcraft.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class AiAgentService {


    @Value("${qwen.api.key}")
    private String apiKey;

    @Value("${qwen.api.url}")
    private String apiUrl;

    @Value("classpath:/prompts/prompt-template.st")
    private Resource promptTemplate;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getResponse(String prompt) {

        //TODO
        //call db with category (received in request and context (returned from createContext(prompt))
        //get context from db (html form)
        String contextFromDb = createContext(prompt);


        JSONObject requestPayload = new JSONObject();
        requestPayload.put("model", "qwen-7b");
        requestPayload.put("input", contextFromDb);


        // Set the headers, including API key
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<String> entity = new HttpEntity<>(requestPayload.toString(), headers);

        // Call the Qwen API
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

        //returning response (html format) in json format
        JSONObject responseJson = new JSONObject(response.getBody());
        return responseJson.getString("response");
    }


    private String createContext(String userPrompt) {

        // Read the content of the file
        String content = null;
        try {
            content = new String(FileCopyUtils.copyToByteArray(promptTemplate.getInputStream()),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Replace placeholders with the actual values
        content = content.replace("{prompt_note}", "some_value")
                .replace("{format}", "some_format");

        return content;
    }
}
