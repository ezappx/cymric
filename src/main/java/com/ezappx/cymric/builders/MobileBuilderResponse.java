package com.ezappx.cymric.builders;

import java.time.LocalDateTime;

public class MobileBuilderResponse {

    private String mobileInstaller;
    private String builderLog;
    private LocalDateTime builtTime;

    public MobileBuilderResponse(String mobileInstaller, String builderLog, LocalDateTime builtTime) {
        this.mobileInstaller = mobileInstaller;
        this.builderLog = builderLog;
        this.builtTime = builtTime;
    }

    public String getMobileInstaller() {
        return mobileInstaller;
    }

    public String getBuilderLog() {
        return builderLog;
    }

    public LocalDateTime getBuiltTime() {
        return builtTime;
    }

    @Override
    public String toString() {
        return "MobileBuilderResponse{" +
                "mobileInstaller='" + mobileInstaller + '\'' +
                ", builderLog='" + builderLog + '\'' +
                ", builtTime=" + builtTime +
                '}';
    }
}
