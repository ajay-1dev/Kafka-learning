package com.ajay.kafkalearning.notification_service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserKafkaConsumer {

    @KafkaListener(topics = "user-topic-message")
    public void handleUserMessage1(String message){
        log.info("Message Received consumer 1:{}",message);
    }

    @KafkaListener(topics = "user-topic-message")
    public void handleUserMessage2(String message){
        log.info("Message Received consumer 2:{}",message);
    }


    @KafkaListener(topics = "user-topic-message")
    public void handleUserMessage3(String message){
        log.info("Message Received consumer 3:{}",message);
    }

}
