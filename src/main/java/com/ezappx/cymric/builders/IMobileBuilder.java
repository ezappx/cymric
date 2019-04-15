package com.ezappx.cymric.builders;


import com.ezappx.cymric.models.UserMobileProject;
import com.ezappx.cymric.properties.MobileBuilderProperties;

public interface IMobileBuilder {
    /**
     * Start a new thread to run this time wasted method
     */
    void build();

    IMobileBuilder setProperties(MobileBuilderProperties properties);

    IMobileBuilder setUserMobileProject(UserMobileProject userMobileProject);

}
