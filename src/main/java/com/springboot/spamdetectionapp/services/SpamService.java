package com.springboot.spamdetectionapp.services;

import com.springboot.spamdetectionapp.pojo.RegisteredUser;
import org.springframework.http.ResponseEntity;

public interface SpamService {

    ResponseEntity<String> reportNumberAsSpam(String phoneNumber, String token);

    ResponseEntity<String> reportNumberAsNotSpam(String phoneNumber,String token);

    ResponseEntity<?> checkNumberForSpam(String phoneNumber,String token);
}
