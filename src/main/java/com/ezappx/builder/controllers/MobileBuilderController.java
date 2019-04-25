package com.ezappx.builder.controllers;


import com.ezappx.builder.config.MobileBuilderProperties;
import com.ezappx.builder.models.UserMobileProject;
import com.ezappx.builder.services.MobileBuilderService;
import com.ezappx.builder.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MobileBuilderController {

    @Autowired
    private MobileBuilderService builderServices;

    @Autowired
    private MobileBuilderProperties properties;

    @PostMapping(value = "/android/build")
    public CommonResponse<String> androidBuild(@RequestBody UserMobileProject userMobileProject) {
        builderServices.androidBuild(userMobileProject);
        CommonResponse<String> response = new CommonResponse<>();
        response.setStatus(HttpStatus.OK);
        response.setMsg(CommonResponse.SUCCESS);
        response.setData(String.format("[server %s:%d] building your project...",
                properties.getIp(), properties.getPort()));
        return response;
    }
}
