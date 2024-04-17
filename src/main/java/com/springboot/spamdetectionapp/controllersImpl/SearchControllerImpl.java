package com.springboot.spamdetectionapp.controllersImpl;

import com.springboot.spamdetectionapp.constants.Constants;
import com.springboot.spamdetectionapp.controllers.SearchController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.spamdetectionapp.services.SearchService;
import com.springboot.spamdetectionapp.utils.userUtils.UserUtils;

@RestController
public class SearchControllerImpl implements SearchController {

    @Autowired
    SearchService searchService;

    @Override
    public ResponseEntity<?> searchByName(String name, String token) {

        try {
            return searchService.searchByName(name, token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> searchByPhoneNumber(String phoneNumber, String token) {

        try {
            return searchService.searchByPhoneNumber(phoneNumber, token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
