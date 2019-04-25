package com.ezappx.builder.builders;

import com.ezappx.builder.utils.ProcessUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class Cordova implements ICordova {
    private static final String CORDOVA = "cordova";

    private Path userBuilderProjectsPath;
    private Path builderProjectsPath;

    public Cordova(Path userBuilderProjectsPath, Path builderProjectsPath) {
        this.userBuilderProjectsPath = userBuilderProjectsPath;
        this.builderProjectsPath = builderProjectsPath;
    }

    @Override
    public void create(String dirName, String packageName, String projectName) throws IOException, InterruptedException {
        new ProcessUtils(userBuilderProjectsPath).exec(Arrays.asList(CORDOVA, "create", dirName, packageName, projectName));
    }

    @Override
    public void addPlatform(String mobilePlatform, String version) throws IOException, InterruptedException {
        new ProcessUtils(builderProjectsPath).exec(Arrays.asList(CORDOVA, "platform", "add", mobilePlatform.toLowerCase() + "@" + version));
    }

    @Override
    public void addPlugin(String pluginName) throws IOException, InterruptedException {
        new ProcessUtils(builderProjectsPath).exec(Arrays.asList(CORDOVA, "plugin", "add", pluginName));
    }

    @Override
    public String build(String mobilePlatform) throws IOException, InterruptedException {
        return new ProcessUtils(builderProjectsPath).exec(Arrays.asList(CORDOVA, "build", mobilePlatform));
    }

}
