package com.ezappx.cymric.utils;

public interface ICordova {
    void create(String dirName, String packageName, String projectName);

    void addPlatform(String mobilePlatform, String version);

    void addPlugin(String pluginName);

    /**
     * Must be invoked otherwise above method does't work.
     *
     * @param mobilePlatform
     */
    String build(String mobilePlatform);
}
