package com.ezappx.builder.builders;


import com.ezappx.builder.config.MobileBuilderProperties;
import com.ezappx.builder.models.UserMobileProject;

public interface IMobileBuilder {
    /**
     * Start a new thread to run this time wasted method
     */
    void build();

    IMobileBuilder setProperties(MobileBuilderProperties properties);

    IMobileBuilder setUserMobileProject(UserMobileProject userMobileProject);

    IMobileBuilder setMobileBuilderResultSender(IMobileBuilderResultSender mobileBuilderResultSender);

}
