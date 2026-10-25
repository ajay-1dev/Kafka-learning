package com.ajay.kafkalearning.user_service.controller;


import com.ajay.kafkalearning.user_service.dto.CreateUserDTO;
import com.ajay.kafkalearning.user_service.event.MessageEvent;
import com.ajay.kafkalearning.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final KafkaTemplate<Long, MessageEvent> kafkaTemplate;
    private final UserService userService;

    @Value("${user_topic_name}")
    private String user_topic_name;

    @PostMapping("/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) {
        for(int i=0; i<100; i++) {
            MessageEvent messageEvent = MessageEvent.builder()
                    .id((long)i%2)
                    .message(message+" "+i)
                    .build();
            kafkaTemplate.send(user_topic_name,messageEvent.getId(),messageEvent);
        }
        return ResponseEntity.ok("message queued");

    }

    @PostMapping()
    public ResponseEntity<CreateUserDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
        CreateUserDTO createUserDTO1 = userService.createUser(createUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserDTO1);
    }

}
