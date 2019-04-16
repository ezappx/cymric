package com.ezappx.cymric.services;

import com.ezappx.cymric.builders.AndroidBuilder;
import com.ezappx.cymric.builders.IMobileBuilder;
import com.ezappx.cymric.builders.factory.MobileBuilderFactory;
import com.ezappx.cymric.models.UserMobileProject;
import com.ezappx.cymric.config.MobileBuilderProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MobileBuilderService {

    @Autowired
    private MobileBuilderFactory factory;

    @Autowired
    private MobileBuilderProperties properties;

    @Autowired
    private MQSenderService senderService;

    public void androidBuild(UserMobileProject project) {
        log.debug("start android build service");
        IMobileBuilder builder = factory.getMobileBuilder(AndroidBuilder.class);
        builder.setProperties(properties)
                .setUserMobileProject(project)
                .setMobileBuilderResultSender(senderService)
                .build();
    }
}
