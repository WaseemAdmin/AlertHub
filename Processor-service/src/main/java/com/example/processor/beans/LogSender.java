package com.example.processor.beans;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class LogSender {

    private final RestTemplate restTemplate;

    public LogSender(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public void sendLog(String level, String serviceName, String message) {
        String url = "http://localhost:8083/logs"; // Logger's endpoint
        Map<String, Object> logMap = new HashMap<>();
        logMap.put("timestamp", LocalDateTime.now().toString());
        logMap.put("logLevel", level);
        logMap.put("serviceName", serviceName);
        logMap.put("message", message);

        try {
            restTemplate.postForEntity(url, logMap, String.class);
        } catch (Exception e) {
            System.err.println("❌ Failed to send log: " + e.getMessage());
        }
    }
}
