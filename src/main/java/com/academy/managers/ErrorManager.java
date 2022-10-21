package com.academy.managers;

import org.json.JSONObject;

public class ErrorManager {
    public static String getJSONStringResponse(String error) {
        return new JSONObject()
                .put("error", error)
                .toString();
    }
}
