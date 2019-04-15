package com.ezappx.cymric.controllers;


import com.ezappx.cymric.builders.AndroidBuilder;
import com.ezappx.cymric.builders.IMobileBuilder;
import com.ezappx.cymric.builders.factory.MobileBuilderFactory;
import com.ezappx.cymric.models.UserMobileProject;
import com.ezappx.cymric.properties.MobileBuilderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MobileBuilderController {

    private Logger log = LoggerFactory.getLogger(MobileBuilderController.class);

    @Autowired
    private MobileBuilderFactory mobileBuilderFactory;

    @Autowired
    private MobileBuilderProperties properties;

    @PostMapping(value = "/android/build")
    public void androidBuild(@RequestBody UserMobileProject userMobileProject) {
        log.info("create android builder");
        IMobileBuilder builder = mobileBuilderFactory.getMobileBuilder(AndroidBuilder.class);
        builder.setProperties(properties).setUserMobileProject(userMobileProject).build();
    }

    @PostMapping("/test")
    public String test() {
        log.debug(properties.getPackageNamePrefix());
        return "test controller";
    }
}
