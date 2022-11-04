package com.academy.managers;

import com.academy.controllers.CoursesController;
import com.academy.controllers.TopicsController;
import com.academy.controllers.UsersController;
import com.academy.services.DatabaseService;
import com.academy.services.LoggerService;
import io.github.cdimascio.dotenv.Dotenv;
import io.javalin.event.EventListener;

import java.sql.SQLException;

/**
 * Handles Javalin lifecycle events.
 * More info: <a href="https://javalin.io/documentation#lifecycle-events">Javalin lifecycle events</a>
 * @author Alexander Schilling
 */
public class EventManager {
    /**
     * Setups handlers for each lifecycle event to be handled
     * @param listener Javalin EventListener
     */
    public static void setupEvents(EventListener listener) {
        listener.serverStarting(EventManager::handleStarting);
        listener.serverStarted(EventManager::handleStarted);
        listener.serverStopping(EventManager::handleStopping);
        listener.serverStopped(EventManager::handleStopped);
    }

    private static void handleStarting() throws SQLException {
        Dotenv dotenv = Dotenv.load();

        LoggerService.info(EventManager.class, "Database is starting...");

        DatabaseService.connect(
                dotenv.get("DB_IP"),
                dotenv.get("DB_NAME"),
                dotenv.get("DB_USERNAME"),
                dotenv.get("DB_PASSWORD")
        );

        LoggerService.info(EventManager.class, "Database connection successful!");
    }

    private static void handleStarted() throws SQLException {
        LoggerService.info(EventManager.class, "Server started!");

        CoursesController.setupCourses();
        TopicsController.setupTopics();
        UsersController.setupUsers();

        LoggerService.info(EventManager.class, String.format("Loaded %d courses, %d topics and %s users!",
                CoursesController.getCourses().size(),
                TopicsController.getTopics().size(),
                UsersController.getUsers().size()
        ));
    }

    private static void handleStopping() throws SQLException {
        LoggerService.info(EventManager.class, "Server is stopping...");

        DatabaseService.disconnect();
    }

    private static void handleStopped() {
        LoggerService.info(EventManager.class, "Server stopped!");
    }
}
