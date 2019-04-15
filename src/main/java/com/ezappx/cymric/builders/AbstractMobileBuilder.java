package com.ezappx.cymric.builders;


import com.ezappx.cymric.models.UserMobileProject;
import com.ezappx.cymric.properties.MobileBuilderProperties;
import com.ezappx.cymric.utils.Cordova;
import com.ezappx.cymric.utils.ICordova;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public abstract class AbstractMobileBuilder implements IMobileBuilder {
    private Logger log = LoggerFactory.getLogger(AbstractMobileBuilder.class);
    protected MobileBuilderProperties properties;
    protected UserMobileProject userMobileProject;
    protected String packageNamePrefix;
    protected Path userBuilderProjectPath;
    protected Path builderProjectPath;
    protected ICordova cordova;

    /**
     * Init builder environment
     */
    protected void initBuilderEnv() {
        if (userMobileProject == null) {
            throw new NullPointerException("UserMobileProject not set");
        }

        packageNamePrefix = properties.getPackageNamePrefix();
        userBuilderProjectPath = Paths.get(properties.getProjectDirName()).resolve(userMobileProject.username);
        builderProjectPath = userBuilderProjectPath.resolve(userMobileProject.projectName);
        cordova = new Cordova(userBuilderProjectPath);
    }

    /**
     * Create local builder project for building
     */
    protected void createBuilderProject() {
        // create user builder projects
        if (!Files.exists(userBuilderProjectPath)) {
            try {
                Files.createDirectories(userBuilderProjectPath);
            } catch (IOException e) {
                log.error(e.toString());
            }
        }

        // create builder projects
        if (!Files.exists(builderProjectPath)) {
            log.info("init ezappx mobile builder project at {}", builderProjectPath.toString());
            cordova.create(
                    userMobileProject.projectName,
                    packageNamePrefix + userMobileProject.packageName,
                    userMobileProject.projectName);
        }
    }

    /**
     * Add cordova plugin that is stored in userMobileProject
     */
    protected void addMobileNativePlugin() {
        for (String plugin : userMobileProject.cordovaPlugins) {
            cordova.addPlugin(plugin);
        }
    }

    public void build() {
        initBuilderEnv();
        createBuilderProject();
        addMobilePlatform();
        addMobileNativePlugin();
    }

    /**
     * Add specified mobile platform
     */
    protected abstract void addMobilePlatform();


    public IMobileBuilder setProperties(MobileBuilderProperties properties) {
        this.properties = properties;
        return this;
    }

    public IMobileBuilder setUserMobileProject(UserMobileProject userMobileProject) {
        this.userMobileProject = userMobileProject;
        return this;
    }
}
