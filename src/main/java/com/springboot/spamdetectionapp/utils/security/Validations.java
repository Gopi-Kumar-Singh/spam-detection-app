package com.springboot.spamdetectionapp.utils.security;

import com.springboot.spamdetectionapp.constants.Constants;

public class Validations {

    public static boolean validateEmail(String email) {
        // Beacause email is optional so empty email is fine.
        if (email.isEmpty()) {
            return true;
        }
        // But if email is not empty then it should be a proper email.
        return email.matches(Constants.emailRegEx);
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return phoneNumber.matches(Constants.phoneNumberRegEx);
    }

    public static boolean validateUserName(String userName) {
        if (userName == null) {
            return false;
        }
        if (userName.length() != 0) {
            return true;
        }
        return false;
    }

    public static boolean validatePassword(String password) {
        if (password == null) {
            return false;
        }
        if (password.length() != 0) {
            return true;
        }

        return false;
    }
}
