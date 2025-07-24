package com.pfa.utils;

import com.pfa.model.User;

public class TestData {

    public static User validUser() {
        return new User("odurokwameee@gmail.com", "Abott123+");
    }

    public static User invalidUser() {
        return new User("invalid@example.com", "wrongpassword");
    }

    public static User emptyUser() {
        return new User("", "");
    }

    public static User emptyPassword() {
        return new User("testuser@example.com", "");
    }

    public static User invalidEmailFormat() {
        return new User("invalid-email", "validpass123");
    }

}
