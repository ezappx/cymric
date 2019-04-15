package com.ezappx.cymric.builders.factory;

import com.ezappx.cymric.builders.IMobileBuilder;
import org.springframework.stereotype.Component;

@Component
public class MobileBuilderFactory {

    public <T extends IMobileBuilder> T getMobileBuilder(Class<?> builderClass) {
        IMobileBuilder mobileBuilder = null;
        try {
            mobileBuilder = (IMobileBuilder) Class.forName(builderClass.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (T) mobileBuilder;
    }
}
