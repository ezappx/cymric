package com.ezappx.cymric.models;

import com.ezappx.cymric.builders.MobileOSTypes;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMobileProject {
    public String username;
    public String projectName;
    public String packageName;
    public String createdAt;
    public String updatedAt;
    public MobileOSTypes mobileOS;
    public List<String> binaryFiles;
    public List<String> cordovaPlugins;
    public String id;
}
