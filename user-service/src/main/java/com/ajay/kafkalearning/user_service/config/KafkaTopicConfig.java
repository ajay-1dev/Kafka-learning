package com.ajay.kafkalearning.user_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${user_topic_name}")
    private String user_topic_name;

    @Value("${user_created_event}")
    private String user_created_event;
    @Bean
    public NewTopic userTopicMessage() {
        return new NewTopic(user_topic_name,3,(short) 1);
    }

    @Bean
    public NewTopic userCreatedEvent() {
        return new NewTopic(user_created_event,4,(short) 1);
    }
}
