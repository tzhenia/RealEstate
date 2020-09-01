package com.tzhenia.real.estate.company.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class MessageUtil {
    public static String getMessage(final String jsonName) throws IOException {
        return IOUtils.toString(MessageUtil.class.getResourceAsStream("/" + jsonName));
    }
}