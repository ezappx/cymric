package com.ezappx.cymric.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MQConfig {
    public static final String ROUTE_DEFAULT = "default";
    public static final String BUILDER_RESULT_MQ = "mobile.builder.result.mq";
    public static final String BUILDER_RESULT_EXCHANGE = "mobile.builder.result.ex";

    @Bean
    public Queue builderResultQueue() {
        return new Queue(BUILDER_RESULT_MQ, true);
    }

    @Bean
    public DirectExchange builderResultExchange() {
        return new DirectExchange(BUILDER_RESULT_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingDefault(DirectExchange builderResultExchange, Queue builderResultQueue) {
        return BindingBuilder.bind(builderResultQueue).to(builderResultExchange).with(ROUTE_DEFAULT);
    }
}
