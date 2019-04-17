package com.ezappx.cymric.builders;

import java.io.IOException;

public interface ICordova {
    void create(String dirName, String packageName, String projectName) throws IOException, InterruptedException;

    void addPlatform(String mobilePlatform, String version) throws IOException, InterruptedException;

    void addPlugin(String pluginName) throws IOException, InterruptedException;

    /**
     * Must be invoked otherwise above method does't work.
     *
     * @param mobilePlatform
     */
    String build(String mobilePlatform) throws IOException, InterruptedException;
}
