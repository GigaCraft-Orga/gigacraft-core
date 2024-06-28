package dev.grafjojo.gigacraftcore.utils;

import dev.grafjojo.gigacraftcore.GigaCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GigaLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(GigaCore.class);

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void warn(String message) {
        LOGGER.warn(message);
    }

    public static void error(String message) {
        LOGGER.error(message);
    }
}
