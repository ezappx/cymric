package com.ezappx.cymric.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@Component
@Entity
@Table(name = "UserMobileProjects")
public class UserMobileProject {

    @Id
    @GeneratedValue
    private String id;

    private String username;

    private String projectName;

    private String packageName;

    private String createdAt;

    private String updatedAt;

    private String mobileOS;

    @ElementCollection
    private List<String> binaryFiles;

    @ElementCollection
    private List<String> cordovaPlugins;
}
