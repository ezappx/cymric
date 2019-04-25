package com.ezappx.builder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MobileBuilderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileBuilderApplication.class, args);
    }

}
