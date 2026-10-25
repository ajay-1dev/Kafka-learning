package com.ajay.kafkalearning.user_service.service;

import com.ajay.kafkalearning.user_service.dto.CreateUserDTO;
import com.ajay.kafkalearning.user_service.entity.User;
import com.ajay.kafkalearning.user_service.event.UserCreatedEvent;
import com.ajay.kafkalearning.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final KafkaTemplate<Long, UserCreatedEvent> kafkaTemplate;


//    @Value("${user_topic_name}")
//    private String user_topic_name;

    @Value("${user_created_event}")
    private String user_created_event;


    public CreateUserDTO createUser(CreateUserDTO createUserDTO) {
        User user = modelMapper.map(createUserDTO, User.class);
        User savedUser = userRepository.save(user);
        UserCreatedEvent userCreatedEvent = modelMapper.map(savedUser, UserCreatedEvent.class);
        kafkaTemplate.send(user_created_event,userCreatedEvent.getId(), userCreatedEvent);
        return modelMapper.map(user, CreateUserDTO.class);
    }
}
