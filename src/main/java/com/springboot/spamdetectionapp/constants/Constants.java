package com.springboot.spamdetectionapp.constants;

public class Constants {

    public static final String emailRegEx = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    public static final String phoneNumberRegEx = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$";
    public static final String REPORTED_SPAM = "User successfully reported as spam. Thank you!";
    public static final String REPORTED_NOT_SPAM = "User successfully reported as not spam. Thank you!";
    public static final String toBypassFilter = "/user/login|/user/register|/user/forgotPassword";
    public static final String INVALID_USERNAME = "Invalid Username.";
    public static final String INVALID_EMAIL = "Invalid Email address.";
    public static final String INVALID_PHONE_NUMBER = "Invalid phone number.";
    public static final String INVALID_CREDENTIAL = "Invalid Credential.";
    public static final String CONTACTS_SYNC_SUCCESSFULLY = "Your contacts is sync successfully.";
    public static final String USER_LOGIN_SUCCESSFULLY = "User login successfully";
    public static final String USER_REGISTERED_SUCCESSFULLY = "User registered successfully.";
    public static final String EXISTING_PHONE_NUMBER = "Phone number is already registered.";
    public static final String CREDENTIAL_UPDATED = "Credential Updated Successfully.";
    public static final String PLEASE_LOGIN = "Please login to access our service.";
    public static final String NO_USER_FOUND ="Oops! No user found.";
    public static final String SOMETHING_WENT_WRONG = "Something went wrong";
    public static final String secretKey = "abcdefghijalkajjr1541043j#*bajnhf";
    public static final int tokenValidDuration = 1000 * 60 * 10;

}
