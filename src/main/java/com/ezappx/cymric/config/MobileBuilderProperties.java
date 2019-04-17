package com.ezappx.cymric.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ezappx.mobile.builder")
@Data
public class MobileBuilderProperties {
    private String projectDirName;
    private String baseDir;
}
