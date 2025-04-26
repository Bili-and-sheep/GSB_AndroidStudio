package com.example.gsb.utils;

public class Utils {
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}