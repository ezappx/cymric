package com.ezappx.builder.builders;

import java.io.IOException;

public class AndroidAppBuilder extends AbstractMobileAppBuilder {

    @Override
    protected void addMobilePlatform() throws IOException, InterruptedException {
        PLATFORM = "android";
        VERSION = "7.1.0";
        super.addMobilePlatform();
    }
}
