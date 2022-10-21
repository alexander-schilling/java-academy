package com.academy.managers;

import com.academy.controllers.CoursesController;
import com.academy.controllers.UsersController;
import io.javalin.Javalin;

public class RoutesManager {
    private static Javalin app;

    public static void setupRoutes(Javalin _app) {
        app = _app;

        setupUsersRoutes();
        setupCoursesRoutes();
    }

    private static void setupUsersRoutes() {
        app.post("users/login", UsersController::Login);
    }

    private static void setupCoursesRoutes() {
        app.get("/courses", CoursesController::Index);
    }
}
