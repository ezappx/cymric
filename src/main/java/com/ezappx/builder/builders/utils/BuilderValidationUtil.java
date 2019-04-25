package com.ezappx.builder.builders.utils;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.Paths;

@Slf4j
public class BuilderValidationUtil {

    public static boolean isValidPackageName(String fullName) {
        if (fullName.isEmpty() || fullName.endsWith(".")) {
            return false;
        }

        String[] names = fullName.split("\\.");
        if (names.length < 2)
            return false;
        for (String name : names) {
            if (!isValidClassName(name)) return false;
        }

        return true;
    }

    private static boolean isValidClassName(String className) {
        if (className.length() == 0)
            return false;

        for (int i = 0; i < className.length(); i++) {
            if (!Character.isAlphabetic(className.charAt(i)))
                return false;
        }

        return true;
    }

    public static boolean isValidInstaller(String installer) {
        try {
            return Paths.get(installer).toFile().exists();
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }
}
