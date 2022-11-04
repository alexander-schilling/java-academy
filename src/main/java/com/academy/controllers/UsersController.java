package com.academy.controllers;

import com.academy.configs.ErrorTypes;
import com.academy.managers.ErrorManager;
import com.academy.managers.TokenManager;
import com.academy.models.User;
import com.academy.models.concerns.user.UserBuilder;
import com.academy.models.concerns.user.UserPersistence;
import com.academy.services.StorageService;
import io.javalin.http.Context;
import org.json.JSONObject;

import java.sql.SQLException;
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
    public static void setupUsers() throws SQLException { users = StorageService.getUsers(); }

    /**
     * Persists the user and adds it to the list
     * @param user user object (without id)
     * @throws SQLException Any SQL Error
     */
    public static void createUser(User user) throws SQLException {
        UserPersistence.createUser(user);
        users.add(user);
    }

    public static void updateUser(User user) throws SQLException {
        UserPersistence.updateUser(user);
    }

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
            context.status(400)
                    .json(ErrorManager.getJSONStringResponse(ErrorTypes.INVALID_VALUE));
            return;
        }

        // Check if user already exists
        User user = UsersController.getUserFromUsername(username);
        if (user != null) {
            context.status(409)
                    .json(ErrorManager.getJSONStringResponse(ErrorTypes.USER_ALREADY_EXISTS));
            return;
        }

        // Check if passwords are equal
        if (!password.equals(passwordConfirmation)) {
            context.status(400)
                    .json(ErrorManager.getJSONStringResponse(ErrorTypes.PASSWORD_MISMATCH));
            return;
        }

        // Create the new user with authentication token
        String token = TokenManager.generateToken();
        User userCreated = new User(username, password, firstName, lastName, token);

        // Add the new user to the list and database
        try {
            UsersController.createUser(userCreated);
            context.status(200).json(UserBuilder.toPrivateJSONObject(userCreated).toString());
        } catch (SQLException e) {
            context.status(500).json(ErrorManager.getJSONStringResponse(ErrorTypes.COULD_NOT_CREATE_USER, e.toString()));
        }
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
            context.status(409)
                    .json(ErrorManager.getJSONStringResponse(ErrorTypes.USER_NOT_FOUND));
            return;
        }

        if (!user.getPassword().equals(password)) {
            context.status(403)
                    .json(ErrorManager.getJSONStringResponse(ErrorTypes.INVALID_PASSWORD));
            return;
        }

        // If login is successful, generate a token to be used in user requests
        String previousToken = user.getToken();
        user.setToken(TokenManager.generateToken());

        try {
            UsersController.updateUser(user);
            context.status(200).json(UserBuilder.toPrivateJSONObject(user).toString());
        } catch (SQLException e) {
            user.setToken(previousToken);
            context.status(500).json(ErrorManager.getJSONStringResponse(ErrorTypes.COULD_NOT_LOGIN, e.toString()));
        }
    }
}
