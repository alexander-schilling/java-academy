package com.academy.managers;

import com.academy.utils.RandomString;

public class TokenManager {
    public static int TOKEN_LENGTH = 16;

    public static String generateToken() {
        return RandomString.getAlphanumericString(TOKEN_LENGTH);
    }
}
