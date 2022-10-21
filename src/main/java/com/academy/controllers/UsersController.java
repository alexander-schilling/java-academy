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

public class UsersController {
    private static List<User> users;

    public static void setupUsers() { users = StorageService.getUsers(); }

    public static List<User> getUsers() { return users; }

    public static User getUserFromId(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    public static User getUserFromUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    public static void Login(Context context) {
        JSONObject body;
        String username;
        String password;

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

        user.setToken(TokenManager.generateToken());

        context.status(200).json(UserBuilder.toPrivateJSONObject(user).toString());
    }
}
