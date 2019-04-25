package com.ezappx.builder.services;

import com.ezappx.builder.builders.AndroidAppBuilder;
import com.ezappx.builder.builders.IMobileBuilder;
import com.ezappx.builder.builders.factory.MobileAppBuilderFactory;
import com.ezappx.builder.config.MessageQueueConfig;
import com.ezappx.builder.config.MobileBuilderProperties;
import com.ezappx.builder.models.UserMobileProject;
import com.ezappx.builder.utils.MobileBuilderResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
@Slf4j
public class MobileBuilderService {

    @Autowired
    private MobileAppBuilderFactory factory;

    @Autowired
    private MobileBuilderProperties properties;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageQueueConfig messageQueueConfig;

    public void androidBuild(UserMobileProject project) {
        log.debug("start android build service");
        IMobileBuilder builder = factory.getMobileBuilder(AndroidAppBuilder.class);
        builder.setProperties(properties)
                .setUserMobileProject(project)
                .setMobileBuilderResultSender(this::sendMobileBuilderResult)
                .setResourceGenerator(this::generateResource)
                .build();
    }

    /**
     * 发送打包结果到消息中间件
     */
    private void sendMobileBuilderResult(MobileBuilderResult result) {
        log.debug("sendMobileBuilderResult: {}", result);

        // TODO 返回的是本地文件地址，需要映射到网址 result.getMobileInstaller()
        rabbitTemplate.convertAndSend(messageQueueConfig.getResultEx(), messageQueueConfig.getResultRoute(), result);
    }

    private void generateResource(UserMobileProject project, Path dest) {
        // TODO 从数据库获取二进制资源，生成文件后添加到Cordova工程
        log.debug("fake generator process {} to {}", project, dest);
    }
}
