package com.example.processor.beans.utils; // change package for email/sms accordingly

import com.example.processor.beans.LogEntry; // or define LogEntry locally in each service
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;

@Component
public class LoggerClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendLog(String serviceName, String level, String message) {
        LogEntry log = new LogEntry();
        log.setTimestamp(LocalDateTime.now());
        log.setServiceName(serviceName);
        log.setLogLevel(level);
        log.setMessage(message);

        try {
            restTemplate.postForObject("http://localhost:8083/logs", log, Void.class);
        } catch (Exception e) {
            System.err.println("❌ Failed to send log to Logger Service: " + e.getMessage());
        }
    }
}
