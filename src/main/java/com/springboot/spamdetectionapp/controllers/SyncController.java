package com.springboot.spamdetectionapp.controllers;

import com.springboot.spamdetectionapp.pojo.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sync")
public interface SyncController {

    @PostMapping("/contacts")
    ResponseEntity<String>syncYourContacts(@RequestBody Contact[] myContacts,@RequestHeader("Authorization") String myPhoneNumber);

}
