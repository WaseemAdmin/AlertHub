package com.example.processor.beans.service;

import com.example.processor.beans.*;
import com.example.processor.beans.feign.LoaderClient;
import com.example.processor.beans.feign.MetricsClients;
import com.example.processor.beans.producer.KafkaNotif;
import com.example.processor.beans.repository.ActionRepository;
import com.example.processor.beans.utils.LoggerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessorService {

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private LoaderClient loaderClient;

    @Autowired
    private MetricsClients metricsClient;

    @Autowired
    private KafkaNotif kafkaProducer;
    @Autowired
    private LoggerClient loggerClient;
    @Autowired
    private LogSender logSender;

    public void processActions() {
        System.out.println("🚀 processActions() is running...");
        logSender.sendLog("INFO", "processor", "Started processing actions.");

        List<ActionEntity> actions = actionRepository.findByIsEnabledTrueAndIsDeletedFalse();
        logSender.sendLog("INFO", "processor", "Found " + actions.size() + " active actions.");

        for (ActionEntity action : actions) {
            try {
                List<PlatformInformation> platformData = loaderClient.getLoaderData(action.getOwnerId());
                List<Metric> metrics = metricsClient.getAllMetrics();

                boolean isConditionMet = false;
                for (Metric metric : metrics) {
                    long matchCount = platformData.stream()
                            .filter(p -> p.getLabel().equals(metric.getLabel().name())
                                    && p.getTimestamp().isAfter(LocalDateTime.now().minusHours(metric.getTimeFrameHours())))
                            .count();

                    if (matchCount >= metric.getThreshold()) {
                        isConditionMet = true;
                        break;
                    }
                }

                if (isConditionMet) {
                    String topic = action.getActionType().name().toLowerCase();
                    kafkaProducer.send(topic, action.getTo(), action.getMessage());

                    action.setLastRun(LocalDateTime.now());
                    actionRepository.save(action);

                    logSender.sendLog("INFO", "processor", "Kafka message sent to " + topic + "-topic for action ID: " + action.getId());
                } else {
                    logSender.sendLog("INFO", "processor", "Condition not met for action ID: " + action.getId());
                }

            } catch (Exception e) {
                logSender.sendLog("ERROR", "processor", "Failed to evaluate condition for action ID: " + action.getId() + " - " + e.getMessage());
                e.printStackTrace();
            }
        }
    }



    private boolean evaluateConditions(ActionEntity action) {
        List<PlatformInformation> platformData = loaderClient.getLoaderData(action.getOwnerId());
        List<Metric> metrics = metricsClient.getAllMetrics();

        for (Metric metric : metrics) {
            long matchCount = platformData.stream()
                    .filter(p -> p.getLabel().equals(metric.getLabel().name())
                            && p.getTimestamp().isAfter(LocalDateTime.now().minusHours(metric.getTimeFrameHours())))
                    .count();

            if (matchCount >= metric.getThreshold()) {
                return true;
            }
        }

        return false;
    }

    public void sendToTargetService(ActionEntity action) {
        String topic = action.getActionType().name().toLowerCase(); // "email" or "sms"
        kafkaProducer.send(topic + "-topic", action.getId().toString(), action);
        System.out.println("✅ Action sent to: " + topic + "-topic");
    }
    public boolean shouldExecuteAction(ActionEntity actionEntity) {
        // 1. Fetch data from Loader
        List<PlatformInformation> dataFromLoader = loaderClient.getLoaderData(actionEntity.getOwnerId());

        // 2. Loop through conditions
        for (Metric condition : actionEntity.getMetricConditions()) {
            boolean conditionMet = dataFromLoader.stream().anyMatch(info -> {
                if (condition.getLabel() == MetricLabel.CPU) {
                    return Integer.parseInt(info.getLabel()) >= condition.getThreshold();
                } else if (condition.getLabel() == MetricLabel.RAM) {
                    return Integer.parseInt(info.getLabel()) >= condition.getThreshold();
                }
                // Add more comparisons if needed
                return false;
            });

            if (!conditionMet) {
                return false; // One condition failed
            }
        }

        return true; // All conditions passed
    }



}
