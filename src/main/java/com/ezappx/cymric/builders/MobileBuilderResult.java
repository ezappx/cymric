package com.ezappx.cymric.builders;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MobileBuilderResult implements Serializable {

    private String mobileInstaller;
    private String builderLog;
    private LocalDateTime builtTime;

    public MobileBuilderResult(String mobileInstaller, String builderLog, LocalDateTime builtTime) {
        this.mobileInstaller = mobileInstaller;
        this.builderLog = builderLog;
        this.builtTime = builtTime;
    }
}
