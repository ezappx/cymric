package com.ezappx.builder.builders;


import com.ezappx.builder.config.MobileBuilderProperties;
import com.ezappx.builder.models.UserMobileProject;
import com.ezappx.builder.utils.MobileBuilderResult;

import java.util.function.Consumer;

public interface IMobileBuilder {
    /**
     * Start a new thread to run this time wasted method
     */
    void build();

    IMobileBuilder setResourceGenerator(IResourceGenerator generator);

    IMobileBuilder setProperties(MobileBuilderProperties properties);

    IMobileBuilder setUserMobileProject(UserMobileProject userMobileProject);

    IMobileBuilder setMobileBuilderResultSender(Consumer<MobileBuilderResult> mobileBuilderResultSender);

}
