package com.springboot.spamdetectionapp.controllers;

import com.springboot.spamdetectionapp.pojo.LoginCredentials;
import com.springboot.spamdetectionapp.pojo.RegistrationCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public interface UserController {

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody RegistrationCredentials registrationCredentials);

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody LoginCredentials loginCredentials);

}
