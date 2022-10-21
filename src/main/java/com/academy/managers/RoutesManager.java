package com.academy.managers;

import com.academy.controllers.CoursesController;
import com.academy.controllers.UsersController;
import io.javalin.Javalin;

/**
 * Handles app routes and calls a controller method reference for each route
 */
public class RoutesManager {
    private static Javalin app;

    /**
     * Links each route to its controller method reference
     * @param _app Javalin app
     */
    public static void setupRoutes(Javalin _app) {
        app = _app;

        setupUsersRoutes();
        setupCoursesRoutes();
    }

    // users route
    private static void setupUsersRoutes() {
        app.post("users/register", UsersController::Register);
        app.post("users/login", UsersController::Login);
    }

    // courses route
    private static void setupCoursesRoutes() {
        app.get("/courses", CoursesController::Index);
    }
}
