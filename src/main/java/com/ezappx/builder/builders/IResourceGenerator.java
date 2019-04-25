package com.ezappx.builder.builders;

import com.ezappx.builder.models.UserMobileProject;

import java.nio.file.Path;

/**
 * 生成Cordova所需的WWW资源文件
 */
@FunctionalInterface
public interface IResourceGenerator {
    void generate(UserMobileProject project, Path dest);
}
