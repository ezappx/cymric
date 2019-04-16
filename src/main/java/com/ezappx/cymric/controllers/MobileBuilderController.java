package com.ezappx.cymric.controllers;


import com.ezappx.cymric.models.UserMobileProject;
import com.ezappx.cymric.services.MobileBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MobileBuilderController {

    @Autowired
    private MobileBuilderService builderServices;

    @PostMapping(value = "/android/build")
    public String androidBuild(@RequestBody UserMobileProject userMobileProject) {
        builderServices.androidBuild(userMobileProject);
        return "The cymric is building your project...";
    }
}
