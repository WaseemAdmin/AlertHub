package com.example.processor.beans;

import java.time.LocalDateTime;

public class LogEntry {

    private String serviceName;
    private String logLevel;
    private String message;
    private LocalDateTime timestamp;

    public LogEntry() {
    }

    public LogEntry(String serviceName, String logLevel, String message, LocalDateTime timestamp) {
        this.serviceName = serviceName;
        this.logLevel = logLevel;
        this.message = message;
        this.timestamp = timestamp;
    }
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
