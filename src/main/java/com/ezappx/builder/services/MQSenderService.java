package com.ezappx.builder.services;

import com.ezappx.builder.builders.IMobileBuilderResultSender;
import com.ezappx.builder.config.MessageQueueConfig;
import com.ezappx.builder.utils.MobileBuilderResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQSenderService implements IMobileBuilderResultSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageQueueConfig messageQueueConfig;

    /**
     * 发送打包结果到消息中间件
     *
     * @param result
     */
    @Override
    public void send(MobileBuilderResult result) {
        log.debug("send: {}", result);
        rabbitTemplate.convertAndSend(messageQueueConfig.getResultEx(), messageQueueConfig.getResultRoute(), result);
    }
}
