package com.ajay.kafkalearning.notification_service.consumer;

import com.ajay.kafkalearning.user_service.event.MessageEvent;
import com.ajay.kafkalearning.user_service.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserKafkaConsumer {

    @KafkaListener(topics = "user-topic-message")
    public void handleUserMessage1(MessageEvent message){
        log.info("Message Received consumer 1:{}",message.toString());
    }

    @KafkaListener(topics = "user-topic-message")
    public void handleUserMessage2(MessageEvent message){
        log.info("Message Received consumer 2:{}",message.toString());
    }


    @KafkaListener(topics = "user-topic-message")
    public void handleUserMessage3(MessageEvent message){
        log.info("Message Received consumer 3:{}",message.toString());
    }


    //consumer for this event: "user-created-event"

    @KafkaListener(topics = "user-created-event")
    public void handleUserCreatedEvent(UserCreatedEvent userCreatedEvent){
        log.info("Message Received From User Created Event Json:{}",userCreatedEvent.toString());
    }
}
