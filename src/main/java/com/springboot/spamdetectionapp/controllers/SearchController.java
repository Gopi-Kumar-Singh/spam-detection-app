package com.springboot.spamdetectionapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/search")
public interface SearchController {

    @GetMapping("/name/{name}")
    ResponseEntity<?> searchByName(@PathVariable("name") String name, @RequestHeader("Authorization") String token);

    @GetMapping("/phone/{phoneNumber}")
    ResponseEntity<?> searchByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber, @RequestHeader("Authorization") String token);
}
