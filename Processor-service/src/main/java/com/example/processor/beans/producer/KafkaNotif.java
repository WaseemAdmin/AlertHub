package com.example.processor.beans.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaNotif {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, String key, Object message) {
        kafkaTemplate.send(topic, key, message);
        System.out.println("📤 Kafka message sent to " + topic);

    }
}
