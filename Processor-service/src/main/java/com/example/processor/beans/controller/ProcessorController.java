package com.example.processor.beans.controller;

import com.example.processor.beans.ActionEntity;
import com.example.processor.beans.LogEntry;
import com.example.processor.beans.LogSender;
import com.example.processor.beans.Metric;
import com.example.processor.beans.repository.ActionRepository;
import com.example.processor.beans.repository.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/test")
public class ProcessorController {

    private final ActionRepository actionRepository;
    private final MetricRepository metricRepository;



    private final LogSender logSender;

    public ProcessorController(ActionRepository actionRepository, MetricRepository metricRepository, LogSender logSender) {
        this.actionRepository = actionRepository;
        this.metricRepository = metricRepository;

        this.logSender = logSender;
    }

    @PostMapping
    public ActionEntity createAction(@RequestBody ActionEntity action) {
        return actionRepository.save(action);
    }

    @GetMapping("/send-log")
    public ResponseEntity<String> testLog() {
        logSender.sendLog("INFO", "processor", "🚀 Test log from processor to logger");
        return ResponseEntity.ok("Log sent");
    }

    @PostMapping("/metrics")
    public ResponseEntity<String> receiveMetric(@RequestBody Metric metric) {
        metricRepository.save(metric); // Save to processor DB
        return ResponseEntity.ok("Metric received and saved.");
    }

}
