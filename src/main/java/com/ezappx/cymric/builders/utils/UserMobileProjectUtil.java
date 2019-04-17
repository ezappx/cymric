package com.ezappx.cymric.builders.utils;

public class UserMobileProjectUtil {

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
        System.out.println(className);
        if (className.length() == 0)
            return false;

        for (int i = 0; i < className.length(); i++){
            if (!Character.isAlphabetic(className.charAt(i)))
                return false;
        }

        return true;
    }
}
