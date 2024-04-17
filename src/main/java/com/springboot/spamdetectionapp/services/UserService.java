package com.springboot.spamdetectionapp.services;

import org.springframework.http.ResponseEntity;
import com.springboot.spamdetectionapp.pojo.LoginCredentials;
import com.springboot.spamdetectionapp.pojo.RegistrationCredentials;

public interface UserService {
    ResponseEntity<String> register(RegistrationCredentials registrationCredentials);
    ResponseEntity<String> login(LoginCredentials loginCredentials);
}
