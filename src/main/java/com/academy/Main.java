package com.academy;

import com.academy.managers.EventManager;
import com.academy.managers.RoutesManager;
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
        Javalin app = Javalin.create(/* config */)
                .events(EventManager::setupEvents)
                .start(7070);

        RoutesManager.setupRoutes(app);
    }
}
