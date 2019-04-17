package com.ezappx.cymric.builders;


import com.ezappx.cymric.config.MobileBuilderProperties;
import com.ezappx.cymric.models.UserMobileProject;
import com.ezappx.cymric.utils.Cordova;
import com.ezappx.cymric.utils.ICordova;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * project/
 * --user1/
 * ----builder-project1/
 * ----builder-project2/
 * --user2/
 * ...
 */
@Slf4j
public abstract class AbstractMobileBuilder implements IMobileBuilder {
    protected MobileBuilderProperties properties;
    protected UserMobileProject userMobileProject;
    protected String packageNamePrefix;
    protected Path userDir;
    protected Path userProjectDir;
    protected ICordova cordova;
    protected IMobileBuilderResultSender mobileBuilderResultSender;

    public IMobileBuilder setProperties(MobileBuilderProperties properties) {
        this.properties = properties;
        return this;
    }

    public IMobileBuilder setUserMobileProject(UserMobileProject userMobileProject) {
        this.userMobileProject = userMobileProject;
        return this;
    }

    public IMobileBuilder setMobileBuilderResultSender(IMobileBuilderResultSender mobileBuilderResultSender) {
        this.mobileBuilderResultSender = mobileBuilderResultSender;
        return this;
    }

    /**
     * Init builder environment
     */
    protected void initBuilderEnv() {
        if (userMobileProject == null) {
            throw new NullPointerException("UserMobileProject not set");
        }

        packageNamePrefix = properties.getPackageNamePrefix();
        userDir = Paths.get(properties.getProjectDirName()).resolve(userMobileProject.username);
        userProjectDir = userDir.resolve(userMobileProject.projectName);
        cordova = new Cordova(userDir, userProjectDir);
    }

    /**
     * Create local builder project for building
     */
    protected void createBuilderProject() throws IOException, InterruptedException {
        // create user builder projects
        if (!Files.exists(userDir)) {
            try {
                Files.createDirectories(userDir);
            } catch (IOException e) {
                log.error(e.toString());
            }
        }

        // create builder projects
        if (!Files.exists(userProjectDir)) {
            log.info("init ezappx mobile builder project at {}", userProjectDir.toString());
            cordova.create(
                    userMobileProject.projectName,
                    packageNamePrefix + userMobileProject.packageName,
                    userMobileProject.projectName);
        }
    }

    /**
     * Add cordova plugin that is stored in userMobileProject
     */
    protected void addMobileNativePlugin() throws IOException, InterruptedException {
        for (String plugin : userMobileProject.cordovaPlugins) {
            cordova.addPlugin(plugin);
        }
    }

    /**
     * Add specified mobile platform
     */
    abstract protected void addMobilePlatform() throws IOException, InterruptedException;

    protected void preStartBuildTask() throws IOException, InterruptedException {
        initBuilderEnv();
        createBuilderProject();
        addMobilePlatform();
        addMobileNativePlugin();
    }

    abstract protected String startBuildTask() throws IOException, InterruptedException;

    abstract public void publishBuilderResult(String builderLog);
}
