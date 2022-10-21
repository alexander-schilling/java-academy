package com.academy.controllers;

import com.academy.configs.ErrorTypes;
import com.academy.managers.ErrorManager;
import com.academy.managers.TokenManager;
import com.academy.models.User;
import com.academy.models.concerns.UserBuilder;
import com.academy.services.StorageService;
import io.javalin.http.Context;
import org.json.JSONObject;

import java.util.List;

/**
 * Controls users in app and routes logic
 * @author Alexander Schilling
 */
public class UsersController {
    private static List<User> users;

    /**
     * Gets users from storage and stores them in users variable
     */
    public static void setupUsers() { users = StorageService.getUsers(); }

    public static List<User> getUsers() { return users; }

    /**
     * @param id User id
     * @return User object or null
     */
    public static User getUserFromId(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    /**
     * @param username A String representing a username
     * @return User object or null
     */
    public static User getUserFromUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    /**
     * Answers the /users/login route with a JSON response representing an error or
     * a User data with a generated authentication token.
     * @param context Javalin http context
     */
    public static void Login(Context context) {
        JSONObject body;
        String username;
        String password;

        // Check if body is properly formatted
        try {
            body = new JSONObject(context.body());

            username = body.getString("username");
            password = body.getString("password");
        } catch (Exception e) {
            context.status(400);
            return;
        }

        User user = UsersController.getUserFromUsername(username);

        if (user == null) {
            context.status(200)
                    .json(ErrorManager.getJSONStringResponse(ErrorTypes.USER_NOT_FOUND));
            return;
        }

        if (!user.getPassword().equals(password)) {
            context.status(200)
                    .json(ErrorManager.getJSONStringResponse(ErrorTypes.INVALID_PASSWORD));
            return;
        }

        // If login is successful, generate a token to be used in user requests
        user.setToken(TokenManager.generateToken());

        context.status(200).json(UserBuilder.toPrivateJSONObject(user).toString());
    }
}
