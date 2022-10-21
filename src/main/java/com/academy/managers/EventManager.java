package com.academy.managers;

import com.academy.controllers.CoursesController;
import com.academy.controllers.TopicsController;
import com.academy.controllers.UsersController;
import com.academy.services.LoggerService;
import io.javalin.event.EventListener;

public class EventManager {
    public static void setupEvents(EventListener listener) {
        listener.serverStarting(EventManager::handleStarting);
        listener.serverStarted(EventManager::handleStarted);
        listener.serverStopping(EventManager::handleStopping);
        listener.serverStopped(EventManager::handleStopped);
    }

    private static void handleStarting() {
        LoggerService.info(EventManager.class, "Server is starting...");
    }

    private static void handleStarted() {
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

    private static void handleStopping() {
        LoggerService.info(EventManager.class, "Server is stopping...");
    }

    private static void handleStopped() {
        LoggerService.info(EventManager.class, "Server stopped!");
    }
}
