package com.ezappx.builder.builders;

import com.ezappx.builder.utils.MobileBuilderResult;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class AndroidAppBuilder extends AbstractMobileAppBuilder {

    private static final String VERSION = "7.1.0"; // TODO Version should be set by Web IDE
    private static final String PLATFORM = "android";

    @Override
    protected void addMobilePlatform() throws IOException, InterruptedException {
        cordova.addPlatform(PLATFORM, VERSION);
    }

    @Override
    protected String startBuildTask() {
        try {
            preStartBuildTask();
            return cordova.build(PLATFORM);
        } catch (Exception e) {
            log.error(e.toString());
            return e.toString();
        }
    }

    @Override
    public void publishBuilderResult(String builderLog) {

        MobileBuilderResult result = new MobileBuilderResult();
        result.setBuiltTime(LocalDateTime.now().toString());
        result.setBuilderLog(builderLog);
        try {
            String installer = builderLog.substring((builderLog.lastIndexOf("\n"))).trim();
            boolean builderSuccess = Paths.get(installer).toFile().exists();
            if (!builderSuccess)  // 未成功生成安装包文件
                log.error("no installer: {}", installer);
            else
                result.setMobileInstaller(installer);
        } catch (Exception e) {
            log.error(e.toString());
        }

        log.debug("publish {}", result);
        mobileBuilderResultSender.send(result);
    }

    @Override
    public void build() {

        // Start new thread to run builder
        CompletableFuture<String> builderFuture = CompletableFuture.supplyAsync(this::startBuildTask);

        // Callback for builder
        builderFuture.thenAccept(this::publishBuilderResult);
    }
}
