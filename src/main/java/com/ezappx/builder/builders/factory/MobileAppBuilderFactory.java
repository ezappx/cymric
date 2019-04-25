package com.ezappx.builder.builders.factory;

import com.ezappx.builder.builders.IMobileBuilder;
import org.springframework.stereotype.Component;

@Component
public class MobileAppBuilderFactory {

    public IMobileBuilder getMobileBuilder(Class<?> builderClass) {

        IMobileBuilder mobileBuilder = null;
        try {
            mobileBuilder = (IMobileBuilder) Class.forName(builderClass.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mobileBuilder;
    }
}
