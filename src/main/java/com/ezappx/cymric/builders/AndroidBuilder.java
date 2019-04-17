package com.ezappx.cymric.builders;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class AndroidBuilder extends AbstractMobileBuilder {

    private static final String VERSION = "7.1.0"; // TODO Version should be set by Web IDE
    private static final String PLATFORM = "android";
    private static final String BUILD_FAILED = "build failed";

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
        MobileBuilderResult result = new MobileBuilderResult(
                "mobile-installer-uri", // TODO setup installer handler
                builderLog,
                LocalDateTime.now()
        );
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
