package com.example.logerr.beans.controller;

import com.example.logerr.beans.LogEntry;
import com.example.logerr.beans.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping
public class LogController {
    @Autowired
    private final LogRepository logRepository;

    @Autowired
    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @GetMapping("/test/send-log")
    public String sendLog() {
        LogEntry entry = new LogEntry();
        entry.setTimestamp(LocalDateTime.now());
        entry.setServiceName("processor");
        entry.setLogLevel("BUG");
        entry.setMessage("Testing log from /test/send-log");

        logRepository.save(entry);
        return "✅ Log sent and saved";
    }
    @PostMapping("/logs")
    public ResponseEntity<String> receiveLog(@RequestBody LogEntry logEntry) {
        logEntry.setTimestamp(LocalDateTime.now());
        logRepository.save(logEntry);
        return ResponseEntity.ok("Log saved");
    }

}
