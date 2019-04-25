package com.ezappx.builder.services;

import com.ezappx.builder.builders.factory.MobileAppBuilderFactory;
import com.ezappx.builder.builders.AndroidAppBuilder;
import com.ezappx.builder.builders.IMobileBuilder;
import com.ezappx.builder.models.UserMobileProject;
import com.ezappx.builder.config.MobileBuilderProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MobileBuilderService {

    @Autowired
    private MobileAppBuilderFactory factory;

    @Autowired
    private MobileBuilderProperties properties;

    @Autowired
    private MQSenderService senderService;

    public void androidBuild(UserMobileProject project) {
        log.debug("start android build service");
        IMobileBuilder builder = factory.getMobileBuilder(AndroidAppBuilder.class);
        builder.setProperties(properties)
                .setUserMobileProject(project)
                .setMobileBuilderResultSender(senderService)
                .build();
    }
}
