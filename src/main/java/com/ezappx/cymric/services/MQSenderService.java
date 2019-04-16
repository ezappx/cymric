package com.ezappx.cymric.services;

import com.ezappx.cymric.builders.IMobileBuilderResultSender;
import com.ezappx.cymric.builders.MobileBuilderResult;
import com.ezappx.cymric.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQSenderService implements IMobileBuilderResultSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void send(MobileBuilderResult result) {
        amqpTemplate.convertAndSend(RabbitMQConfig.BUILDER_RESULT_MQ, result);
    }
}
