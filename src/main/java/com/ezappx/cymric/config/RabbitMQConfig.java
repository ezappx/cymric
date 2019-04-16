package com.ezappx.cymric.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitMQConfig {
    public static final String BUILDER_RESULT_MQ = "mobile-builder-result-mq";

    @Bean
    public Queue queue() {
        return new Queue(BUILDER_RESULT_MQ);
    }
}
