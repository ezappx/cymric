package com.ezappx.cymric.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HiService {

    @Value("${server.port}")
    private String port;

    public String hi(String name) {
        return "hi " + name + " , i am from port:" + port;
    }
}
