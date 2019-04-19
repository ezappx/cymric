package com.ezappx.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MobileBuilderResult implements Serializable {
    private String mobileInstaller;
    private String builderLog;
    private LocalDateTime builtTime;
}

