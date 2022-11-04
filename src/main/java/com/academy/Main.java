package com.academy;

import com.academy.managers.EventManager;
import com.academy.managers.RoutesManager;
import com.academy.services.LoggerService;
import io.javalin.Javalin;

/**
 * Java Academy main class
 * @author Alexander Schilling
 */
public class Main {
    /**
     * Creates the Javalin app and setups routes
     */
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.plugins.enableCors(cors -> {
                cors.add(it -> {
                    LoggerService.info("Beware, allowing every origin! (*)");
                    it.anyHost();
                });
            });
                })
                .events(EventManager::setupEvents)
                .start(7070);

        RoutesManager.setupRoutes(app);
    }
}
