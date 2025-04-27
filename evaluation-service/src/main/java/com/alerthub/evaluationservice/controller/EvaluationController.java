package com.alerthub.evaluationservice.controller;

import com.alerthub.evaluationservice.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/evaluate")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping
    public Map<String, Integer> evaluate(@RequestParam String sprint) {
        return evaluationService.evaluateDeveloperPerformance(sprint);
    }
}
