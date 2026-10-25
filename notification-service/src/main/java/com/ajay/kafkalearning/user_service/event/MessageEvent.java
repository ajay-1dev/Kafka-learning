package com.ajay.kafkalearning.user_service.event;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MessageEvent {

    private Long id;
    private String message;
}
