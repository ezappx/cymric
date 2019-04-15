package com.ezappx.cymric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CymricApplication {

    public static void main(String[] args) {
        SpringApplication.run(CymricApplication.class, args);
    }

}
