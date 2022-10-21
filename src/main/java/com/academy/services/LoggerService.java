package com.academy.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerService {
    public static Logger getLogger(String name) {
        return LoggerFactory.getLogger(name);
    }

    public static Logger getLogger(Class<?> classRef) {
        return LoggerFactory.getLogger(classRef);
    }

    public static void trace(String message) {
        Logger logger = getLogger("default");
        logger.trace(message);
    }

    public static void trace(String name, String message) {
        Logger logger = getLogger(name);
        logger.trace(message);
    }

    public static void trace(Class<?> classRef, String message) {
        Logger logger = getLogger(classRef);
        logger.trace(message);
    }

    public static void info(String message) {
        Logger logger = getLogger("default");
        logger.info(message);
    }

    public static void info(String name, String message) {
        Logger logger = getLogger(name);
        logger.info(message);
    }

    public static void info(Class<?> classRef, String message) {
        Logger logger = getLogger(classRef);
        logger.info(message);
    }

    public static void error(String message) {
        Logger logger = getLogger("default");
        logger.error(message);
    }

    public static void error(String name, String message) {
        Logger logger = getLogger(name);
        logger.error(message);
    }

    public static void error(Class<?> classRef, String message) {
        Logger logger = getLogger(classRef);
        logger.error(message);
    }
}
