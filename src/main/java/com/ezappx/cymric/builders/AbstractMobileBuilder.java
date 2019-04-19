package com.ezappx.cymric.builders;


import com.ezappx.cymric.builders.utils.UserMobileProjectUtil;
import com.ezappx.cymric.config.MobileBuilderProperties;
import com.ezappx.cymric.models.UserMobileProject;
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

        userDir = Paths.get(properties.getProjectDirName()).resolve(userMobileProject.getUsername());
        userProjectDir = userDir.resolve(userMobileProject.getProjectName());
        cordova = new Cordova(userDir, userProjectDir);
    }

    /**
     * Create local builder project for building
     */
    protected void createBuilderProject() throws IOException, InterruptedException, IllegalArgumentException {
        // create user builder projects
        if (!Files.exists(userDir)) {
            try {
                Files.createDirectories(userDir);
            } catch (IOException e) {
                log.error(e.toString());
            }
        }

        if(!UserMobileProjectUtil.isValidPackageName(userMobileProject.getPackageName())) {
            throw new IllegalArgumentException("Not valid packageName");
        }

        // create builder projects
        if (!Files.exists(userProjectDir)) {
            log.info("init ezappx mobile builder project at {}", userProjectDir.toString());
            cordova.create(
                    userMobileProject.getProjectName(),
                    userMobileProject.getPackageName(),
                    userMobileProject.getProjectName());
        }
    }

    /**
     * Add cordova plugin that is stored in userMobileProject
     */
    protected void addMobileNativePlugin() throws IOException, InterruptedException {
        for (String plugin : userMobileProject.getCordovaPlugins()) {
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
