package com.ajay.kafkalearning.user_service.event;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserCreatedEvent {

    private Long id;
    private String userName;
    private String email;
}
