package com.academy.managers;

import org.json.JSONObject;

/**
 * Handles errors and responses for them
 * @author Alexander Schilling
 */
public class ErrorManager {
    /**
     * @param error Error identifier
     * @return JSON String with an error key and value
     */
    public static String getJSONStringResponse(String error) {
        return new JSONObject()
                .put("error", error)
                .toString();
    }
}
