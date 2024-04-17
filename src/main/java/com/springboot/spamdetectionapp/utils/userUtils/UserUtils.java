package com.springboot.spamdetectionapp.utils.userUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class UserUtils {

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String>("{\"message\":\"" + responseMessage + "\"}", httpStatus);
    }

    public static String getPhoneNumber(String token) {
        return token.replace("Bearer ", "");
    }

}
