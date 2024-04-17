package com.springboot.spamdetectionapp.controllersImpl;

import com.springboot.spamdetectionapp.constants.Constants;
import com.springboot.spamdetectionapp.pojo.Contact;
import com.springboot.spamdetectionapp.services.SyncService;
import com.springboot.spamdetectionapp.utils.userUtils.UserUtils;
import com.springboot.spamdetectionapp.controllers.SyncController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SyncControllerImpl implements SyncController {

    @Autowired
    SyncService syncService;

    @Override
    public ResponseEntity<String> syncYourContacts(Contact[] myContacts, String token) {
        try {
            return syncService.syncYourContacts(myContacts, token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
