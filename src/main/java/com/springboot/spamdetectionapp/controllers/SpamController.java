package com.springboot.spamdetectionapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/spam")
public interface SpamController {

    @PostMapping("/report_spam/{phoneNumber}")
    ResponseEntity<String> reportNumberAsSpam(@PathVariable("phoneNumber") String phoneNumber, @RequestHeader("Authorization") String token);

    @PostMapping("/report_not_spam/{phoneNumber}")
    ResponseEntity<String> reportNumberAsNotSpam(@PathVariable("phoneNumber") String phoneNumber, @RequestHeader("Authorization") String token);

    @GetMapping("/check_spam/{phoneNumber}")
    ResponseEntity<?> checkNumberForSpam(@PathVariable("phoneNumber") String phoneNumber, @RequestHeader("Authorization") String token);
}
