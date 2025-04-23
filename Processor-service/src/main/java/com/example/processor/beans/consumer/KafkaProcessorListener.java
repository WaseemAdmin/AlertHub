package com.example.processor.beans.consumer;

import com.example.processor.beans.ActionEntity;
import com.example.processor.beans.PlatformInformation;
import com.example.processor.beans.feign.LoaderClient;
import com.example.processor.beans.producer.KafkaNotif;
import com.example.processor.beans.service.ProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaProcessorListener {
/*
    private final LoaderClient loaderClient;
    private final KafkaNotif kafkaNotif;

    @KafkaListener(topics = "actions-topic", groupId = "processor-group", containerFactory = "kafkaListenerContainerFactory")
    public void receiveAction(ActionEntity actionEntity) {
        System.out.println("🔁 Received Action from Kafka: " + actionEntity.getId());

        List<PlatformInformation> platformData = loaderClient.getLoaderData(actionEntity.getOwnerId());

        boolean conditionMet = platformData.stream().anyMatch(
                info -> info.getLabel().equalsIgnoreCase(actionEntity.getLabel()) // Example condition
        );

        if (conditionMet) {
            String topic = actionEntity.getActionType().name().toLowerCase(); // "email" or "sms"
            kafkaNotif.send(topic, actionEntity.getTo(), actionEntity.getMessage());
            System.out.println("✅ Sent to " + topic + " service");
        } else {
            System.out.println("❌ Condition NOT met. Skipping message.");
        }
    }*/


    @Autowired
    private LoaderClient loaderClient;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private ProcessorService processorService;
        @KafkaListener(topics = "actions-topic", groupId = "processor-group")
        public void processKafkaAction(ActionEntity action) {
            if (processorService.shouldExecuteAction(action)) {
                processorService.sendToTargetService(action);
            } else {
                System.out.println("❌ Action skipped: condition not met");
            }
        }
    }


