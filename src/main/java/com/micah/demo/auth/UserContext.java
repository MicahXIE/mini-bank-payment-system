package com.micah.demo.auth;

public final class UserContext {

    private static final ThreadLocal<String> user = new ThreadLocal<String>();

    public static void add(String userName) {
        user.set(userName);
    }

    public static void remove() {
        user.remove();
    }

    public static String getCurrentUserName() {
        return user.get();
    }
}
