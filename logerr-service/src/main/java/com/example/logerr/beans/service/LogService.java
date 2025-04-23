package com.example.logerr.beans.service;

import com.example.logerr.beans.LogEntry;
import com.example.logerr.beans.repository.LogRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final LogRepository logRepository;

    // ✅ Constructor to inject LogRepository
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void saveLog(LogEntry logEntry) {
        logRepository.save(logEntry);
    }
}
