package com.ezappx.builder.config;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ezappx.rabbitmq")
@Slf4j
@Data
public class MessageQueueConfig {
    private String resultRoute;
    private String resultMq;
    private String resultEx;

    @Bean
    public Queue builderResultQueue() {
        return new Queue(resultMq, true);
    }

    @Bean
    public DirectExchange builderResultExchange() {
        return new DirectExchange(resultEx, true, false);
    }

    @Bean
    public Binding bindingDefault(DirectExchange builderResultExchange, Queue builderResultQueue) {
        return BindingBuilder.bind(builderResultQueue).to(builderResultExchange).with(resultRoute);
    }

    @Bean
    public RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }
}
