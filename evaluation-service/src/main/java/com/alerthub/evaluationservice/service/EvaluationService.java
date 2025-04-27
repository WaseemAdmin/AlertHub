package com.alerthub.evaluationservice.service;

import com.alerthub.evaluationservice.feign.PlatformInformationClient;
import com.alerthub.evaluationservice.model.PlatformInformationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EvaluationService {

    @Autowired
    private PlatformInformationClient loaderClient;

    public Map<String, Integer> evaluateDeveloperPerformance(String sprint) {
        List<PlatformInformationDto> allTasks = loaderClient.getAllPlatformInformation();

        // Filter by sprint
        List<PlatformInformationDto> tasksThisSprint = allTasks.stream()
                .filter(task -> sprint.equals(task.getSprint()))
                .collect(Collectors.toList());

        // Group by developer and sum points
        Map<String, Integer> developerPoints = new HashMap<>();
        for (PlatformInformationDto task : tasksThisSprint) {
        	String developerId = String.valueOf(task.getDeveloperId());
            int points = task.getTaskPoint();
            developerPoints.put(developerId, developerPoints.getOrDefault(developerId, 0) + points);
        }

        return developerPoints;
    }
}
