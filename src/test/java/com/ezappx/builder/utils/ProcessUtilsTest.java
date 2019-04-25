package com.ezappx.builder.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProcessUtilsTest {

    @Test
    public void lineSperator() {
        StringBuilder builder = new StringBuilder();
        builder.append(System.lineSeparator());
        builder.append("ing");
        builder.append(System.lineSeparator());
        builder.append("byr");

        String s = builder.toString();
        System.out.println(s);

        String ns = s.substring(s.lastIndexOf(System.lineSeparator()));
        System.out.println(ns);
    }
}