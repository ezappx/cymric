package com.ezappx.cymric.builders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

public class AndroidBuilder extends AbstractMobileBuilder {

    private static final String VERSION = "7.1.0"; // TODO Version should be set by Web IDE
    private static final String PLATFORM = "android";

    private Logger log = LoggerFactory.getLogger(AndroidBuilder.class);

    private class AndroidBuilderTask {
        public String startBuild() {
            return cordova.build(PLATFORM);
        }

        public void publishBuilderResult(String builderLog) {
            // TODO publish to rabbit mq
            log.debug("publish builder response");
            MobileBuilderResponse response = new MobileBuilderResponse(
                    "mobile-installer-uri",
                    builderLog,
                    LocalDateTime.now()
            );

            log.debug("published : " + response);
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
