package com.academy;

import com.academy.managers.EventManager;
import com.academy.managers.RoutesManager;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(/* config */)
                .events(EventManager::setupEvents)
                .start(7070);

        RoutesManager.setupRoutes(app);
    }
}
