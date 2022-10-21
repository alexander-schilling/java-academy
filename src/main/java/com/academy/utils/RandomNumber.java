package com.academy.utils;

/**
 * Useful functions related with random numbers
 * @author Alexander Schilling
 */
public class RandomNumber {
    /**
     * Generates a random number between range
     * @param min minimum value
     * @param max maximum value
     * @return random integer
     */
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
