package com.academy.managers;

import com.academy.utils.RandomString;

/**
 * Handles user token generation
 * @author Alexander Schilling
 */
public class TokenManager {
    public static int TOKEN_LENGTH = 16;

    public static String generateToken() {
        return RandomString.getAlphanumericString(TOKEN_LENGTH);
    }
}
