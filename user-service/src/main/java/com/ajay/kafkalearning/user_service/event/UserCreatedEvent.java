package com.ajay.kafkalearning.user_service.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreatedEvent {

    private Long id;
    private String userName;
    private String email;
}
