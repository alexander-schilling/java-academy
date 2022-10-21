package com.academy.utils;

/**
 * Useful functions related with random strings
 * @author @Rajput-Ji
 */
public class RandomString {
    // Valid characters for the strings
    private static final String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";

    /**
     * Generates a random string of given length with alphanumeric values
     * @param stringLength String length
     * @return Random String of given length
     */
    public static String getAlphanumericString(int stringLength) {
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(stringLength);

        for (int i = 0; i < stringLength; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(alphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(alphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
