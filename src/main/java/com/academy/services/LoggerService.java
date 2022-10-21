package com.academy.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logs messages to the desired output, currently only on Javalin console
 * @author Alexander Schilling
 */
public class LoggerService {
    /**
     * @param name Logger identifier
     * @return Logger object
     */
    public static Logger getLogger(String name) {
        return LoggerFactory.getLogger(name);
    }

    /**
     * @param classRef Logger identifier
     * @return Logger object
     */
    public static Logger getLogger(Class<?> classRef) {
        return LoggerFactory.getLogger(classRef);
    }

    /**
     * Logs the message as trace
     * @param message Message to log
     */
    public static void trace(String message) {
        Logger logger = getLogger("default");
        logger.trace(message);
    }

    /**
     * Logs the message as trace
     * @param name Logger identifier
     * @param message Message to log
     */
    public static void trace(String name, String message) {
        Logger logger = getLogger(name);
        logger.trace(message);
    }

    /**
     * Logs the message as trace
     * @param classRef Logger identifier
     * @param message Message to log
     */
    public static void trace(Class<?> classRef, String message) {
        Logger logger = getLogger(classRef);
        logger.trace(message);
    }

    /**
     * Logs the message as info
     * @param message Message to log
     */
    public static void info(String message) {
        Logger logger = getLogger("default");
        logger.info(message);
    }

    /**
     * Logs the message as info
     * @param name Logger identifier
     * @param message Message to log
     */
    public static void info(String name, String message) {
        Logger logger = getLogger(name);
        logger.info(message);
    }

    /**
     * Logs the message as info
     * @param classRef Logger identifier
     * @param message Message to log
     */
    public static void info(Class<?> classRef, String message) {
        Logger logger = getLogger(classRef);
        logger.info(message);
    }

    /**
     * Logs the message as error
     * @param message Message to log
     */
    public static void error(String message) {
        Logger logger = getLogger("default");
        logger.error(message);
    }

    /**
     * Logs the message as error
     * @param name Logger identifier
     * @param message Message to log
     */
    public static void error(String name, String message) {
        Logger logger = getLogger(name);
        logger.error(message);
    }

    /**
     * Logs the message as error
     * @param classRef Logger identifier
     * @param message Message to log
     */
    public static void error(Class<?> classRef, String message) {
        Logger logger = getLogger(classRef);
        logger.error(message);
    }
}
