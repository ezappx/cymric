package com.ezappx.builder.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
@ConfigurationProperties(prefix = "ezappx.mobile.builder")
@Data
public class MobileBuilderProperties {
    private String projectDirName;
    private String baseDir;

    @Value("${server.port}")
    private int port;

    private String ip = InetAddress.getLoopbackAddress().getHostAddress();
}
