package com.springboot.spamdetectionapp.services;

import org.springframework.http.ResponseEntity;

public interface SearchService {

    ResponseEntity<?> searchByName(String name, String token);

    ResponseEntity<?> searchByPhoneNumber(String phoneNumber, String token);
}
