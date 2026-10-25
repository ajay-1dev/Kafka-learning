package com.ajay.kafkalearning.user_service.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${user_topic_name}")
    private String user_topic_name;

    @PostMapping("/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) {
        for(int i=0;i<100;i++) {
            kafkaTemplate.send(user_topic_name, ""+i%3,message+i);
        }
        return ResponseEntity.ok("message queued");

    }

}
