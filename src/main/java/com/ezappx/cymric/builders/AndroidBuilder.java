package com.ezappx.cymric.builders;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class AndroidBuilder extends AbstractMobileBuilder {

    private static final String VERSION = "7.1.0"; // TODO Version should be set by Web IDE
    private static final String PLATFORM = "android";

    private class AndroidBuilderTask {
        public String startBuild() {
            return cordova.build(PLATFORM);
        }

        public void publishBuilderResult(String builderLog) {

            log.debug("publish builder result");
            MobileBuilderResult result = new MobileBuilderResult(
                    "mobile-installer-uri",
                    builderLog,
                    LocalDateTime.now()
            );

            mobileBuilderResultSender.send(result);
        }
    }

    private AndroidBuilderTask builderTask = new AndroidBuilderTask();

    @Override
    protected void addMobilePlatform() {
        cordova.addPlatform(PLATFORM, VERSION);
    }

    @Override
    public void build() {
        // Common builder steps
        super.build();

        // Start new thread to run builder
        CompletableFuture<String> builderFuture = CompletableFuture.supplyAsync(builderTask::startBuild);

        // Callback for builder
        builderFuture.thenAccept(builderTask::publishBuilderResult);
    }
}
