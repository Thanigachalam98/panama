package com.trump.panama.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private Logger log = LoggerFactory.getLogger(MessageProducer.class);

    @Autowired 
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${myapp.kafka.topic}")
    private String topic;

    public void sendMessage(String message) {
        log.info("Sending message: " + message);
        kafkaTemplate.send(topic, message);
        
    }
}
