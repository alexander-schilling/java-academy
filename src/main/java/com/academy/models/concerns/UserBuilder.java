package com.academy.models.concerns;

import com.academy.models.User;
import org.json.JSONObject;

public class UserBuilder {
    public static JSONObject toPublicJSONObject(User user) {
        JSONObject json = new JSONObject();

        json.put("id", user.getId());
        json.put("username", user.getUsername());
        json.put("firstName", user.getFirstName());
        json.put("lastName", user.getLastName());

        return json;
    }

    public static JSONObject toPrivateJSONObject(User user) {
        JSONObject json = toPublicJSONObject(user);

        json.put("token", user.getToken());

        return json;
    }
}
