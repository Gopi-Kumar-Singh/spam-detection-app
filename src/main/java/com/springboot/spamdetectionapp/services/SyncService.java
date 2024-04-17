package com.springboot.spamdetectionapp.services;

import com.springboot.spamdetectionapp.pojo.Contact;
import org.springframework.http.ResponseEntity;

public interface SyncService {

    ResponseEntity<String> syncYourContacts(Contact[] myContacts,String token);

}
