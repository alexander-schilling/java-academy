package com.academy.models.concerns.user;

import com.academy.models.User;
import org.json.JSONObject;

/**
 * This handles different types of responses for the User object
 * @author Alexander Schilling
 */
public class UserBuilder {
    /**
     * Returns a JSONObject with non-sensitive User data
     * @param user User object
     * @return JSONObject with id, username, firstName and lastName
     */
    public static JSONObject toPublicJSONObject(User user) {
        JSONObject json = new JSONObject();

        json.put("id", user.getId());
        json.put("username", user.getUsername());
        json.put("firstName", user.getFirstName());
        json.put("lastName", user.getLastName());

        return json;
    }

    /**
     * Returns a JSONObject with sensitive User data
     * @param user User object
     * @return JSONObject with id, username, firstName, lastName and token
     */
    public static JSONObject toPrivateJSONObject(User user) {
        JSONObject json = toPublicJSONObject(user);

        json.put("token", user.getToken());

        return json;
    }
}
