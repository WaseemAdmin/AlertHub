package com.example.logerr.beans.consumer;

import com.example.logerr.beans.LogEntry;
import com.example.logerr.beans.service.LogService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaLogConsumer {

    private final LogService logService;

    // ✅ Constructor to inject LogService
    public KafkaLogConsumer(LogService logService) {
        this.logService = logService;
    }

    @KafkaListener(topics = "log-topic", groupId = "logger-group")
    public void listen(String message) {
        LogEntry entry = new LogEntry();
        entry.setTimestamp(java.time.LocalDateTime.now());
        entry.setServiceName("processor"); // Or set dynamically
        entry.setLogLevel("INFO");
        entry.setMessage(message);
        logService.saveLog(entry);
    }
}
