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

    public static void addUser(User user) { users.add(user); }

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
     * Answers the /users/register route with a JSON response representing an error
     * or User data with a created User and a generated authentication token
     * @param context Javalin http context
     */
    public static void Register(Context context) {
        String username;
        String password;
        String passwordConfirmation;
        String firstName;
        String lastName;

        // Check if body is properly formatted
        try {
            JSONObject body = new JSONObject(context.body());

            username = body.getString("username");
            password = body.getString("password");
            passwordConfirmation = body.getString("passwordConfirmation");
            firstName = body.getString("firstName");
            lastName = body.getString("lastName");
        } catch (Exception e) {
            context.status(400);
            return;
        }

        // Check if any field is blank
        if (username.isBlank() || password.isBlank() || firstName.isBlank() || lastName.isBlank()) {
            context.status(200)
                    .json(ErrorManager.getJSONStringResponse(ErrorTypes.INVALID_VALUE));
            return;
        }

        // Check if user already exists
        User user = UsersController.getUserFromUsername(username);
        if (user != null) {
            context.status(200)
                    .json(ErrorManager.getJSONStringResponse(ErrorTypes.USER_ALREADY_EXISTS));
            return;
        }

        // Check if passwords are equal
        if (!password.equals(passwordConfirmation)) {
            context.status(200)
                    .json(ErrorManager.getJSONStringResponse(ErrorTypes.PASSWORD_MISMATCH));
            return;
        }

        // Create the new user
        List<User> userList = UsersController.getUsers();
        int userId = userList.size() + 1;
        User userCreated = new User(userId, username, password, firstName, lastName);

        // Add the new user to the list
        UsersController.addUser(userCreated);

        // Generate and set the authentication token
        userCreated.setToken(TokenManager.generateToken());

        context.status(200).json(UserBuilder.toPrivateJSONObject(userCreated).toString());
    }

    /**
     * Answers the /users/login route with a JSON response representing an error
     * or User data with a generated authentication token
     * @param context Javalin http context
     */
    public static void Login(Context context) {
        String username;
        String password;

        // Check if body is properly formatted
        try {
            JSONObject body = new JSONObject(context.body());

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
