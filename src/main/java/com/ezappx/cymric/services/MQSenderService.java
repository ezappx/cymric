package com.ezappx.cymric.services;

import com.ezappx.cymric.builders.IMobileBuilderResultSender;
import com.ezappx.common.MobileBuilderResult;
import com.ezappx.cymric.config.MQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQSenderService implements IMobileBuilderResultSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * Send the builder log to rabbit mq
     *
     * @param result
     */
    @Override
    public void send(MobileBuilderResult result) {
        amqpTemplate.convertAndSend(MQConfig.BUILDER_RESULT_EXCHANGE, MQConfig.ROUTE_DEFAULT, result);
    }
}
