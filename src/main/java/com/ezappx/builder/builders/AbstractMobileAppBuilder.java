package com.ezappx.builder.builders;


import com.ezappx.builder.builders.utils.BuilderValidationUtil;
import com.ezappx.builder.config.MobileBuilderProperties;
import com.ezappx.builder.models.UserMobileProject;
import com.ezappx.builder.utils.MobileBuilderResult;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * project/
 * --user1/
 * ----builder-project1/
 * ----builder-project2/
 * --user2/
 * ...
 */
@Slf4j
public abstract class AbstractMobileAppBuilder implements IMobileBuilder {
    private MobileBuilderProperties properties;
    private UserMobileProject userMobileProject;
    private Path userDir;
    private Path userProjectDir;
    private Consumer<MobileBuilderResult> sendMobileBuilderResult;
    private IResourceGenerator generator;

    private ICordova cordova;
    String PLATFORM;
    String VERSION;

    @Override
    public IMobileBuilder setProperties(MobileBuilderProperties properties) {
        this.properties = properties;
        return this;
    }

    @Override
    public IMobileBuilder setUserMobileProject(UserMobileProject userMobileProject) {
        this.userMobileProject = userMobileProject;
        return this;
    }

    @Override
    public IMobileBuilder setMobileBuilderResultSender(Consumer<MobileBuilderResult> mobileBuilderResultSender) {
        this.sendMobileBuilderResult = mobileBuilderResultSender;
        return this;
    }

    @Override
    public IMobileBuilder setResourceGenerator(IResourceGenerator generator) {
        this.generator = generator;
        return this;
    }

    @Override
    public void build() {
        // Start new thread to run builder
        CompletableFuture<MobileBuilderResult> builderFuture = CompletableFuture.supplyAsync(this::startBuildTask);

        // Callback for builder
        builderFuture.thenAccept(this.sendMobileBuilderResult);
    }


    /**
     * Init builder environment
     */
    private void initBuilderEnv() {
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
    private void createBuilderProject() throws IOException, InterruptedException, IllegalArgumentException {
        // create user builder projects
        if (!Files.exists(userDir)) {
            try {
                Files.createDirectories(userDir);
            } catch (IOException e) {
                log.error(e.toString());
            }
        }

        if (!BuilderValidationUtil.isValidPackageName(userMobileProject.getPackageName())) {
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
    private void addMobileNativePlugin() throws IOException, InterruptedException {
        for (String plugin : userMobileProject.getCordovaPlugins()) {
            cordova.addPlugin(plugin);
        }
    }

    /**
     * Add specified mobile platform
     */
    protected void addMobilePlatform() throws IOException, InterruptedException {
        cordova.addPlatform(PLATFORM, VERSION);
    }

    MobileBuilderResult startBuildTask() {
        MobileBuilderResult result = new MobileBuilderResult();
        result.setBuiltTime(LocalDateTime.now().toString());

        try {
            initBuilderEnv();
            createBuilderProject();
            addMobilePlatform();
            addMobileNativePlugin();
            generator.generate(userMobileProject, userProjectDir.resolve("www"));
            String builderLog = cordova.build(PLATFORM);
            result.setBuilderLog(builderLog);

            // 解析安装包文件路径
            String installer = builderLog.substring((builderLog.lastIndexOf(System.lineSeparator()))).trim();
            boolean builderSuccess = Paths.get(installer).toFile().exists();
            if (!builderSuccess)  // 未成功生成安装包文件
                log.error("no installer: {}", installer);
            else
                result.setMobileInstaller(installer);

        } catch (Exception e) {
            log.error(e.toString());
        }

        return result;
    }
}
