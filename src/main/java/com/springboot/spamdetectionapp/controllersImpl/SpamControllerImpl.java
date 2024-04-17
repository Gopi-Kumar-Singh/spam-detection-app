package com.springboot.spamdetectionapp.controllersImpl;

import com.springboot.spamdetectionapp.constants.Constants;
import com.springboot.spamdetectionapp.controllers.SpamController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.spamdetectionapp.services.SpamService;
import com.springboot.spamdetectionapp.utils.userUtils.UserUtils;

@RestController
public class SpamControllerImpl implements SpamController {

    @Autowired
    SpamService spamService;

    @Override
    public ResponseEntity<String> reportNumberAsSpam(String phoneNumber, String token) {

        try {
            return spamService.reportNumberAsSpam(phoneNumber, token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> reportNumberAsNotSpam(String phoneNumber, String token) {

        try {
            return spamService.reportNumberAsNotSpam(phoneNumber, token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> checkNumberForSpam(String phoneNumber, String token) {

        try {
            return spamService.checkNumberForSpam(phoneNumber, token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
