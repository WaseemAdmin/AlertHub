package com.mst.AlertHub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mst.AlertHub.beans.LoginUsers;

@Service
public class KafkaProducerService {


    private static final String TOPIC = "user-login-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendLoginEvent(String event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println("Published login event to Kafka");
    }

}
