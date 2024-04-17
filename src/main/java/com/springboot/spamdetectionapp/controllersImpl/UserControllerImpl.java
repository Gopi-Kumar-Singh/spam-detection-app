package com.springboot.spamdetectionapp.controllersImpl;

import com.springboot.spamdetectionapp.constants.Constants;
import com.springboot.spamdetectionapp.controllers.UserController;
import com.springboot.spamdetectionapp.pojo.LoginCredentials;
import com.springboot.spamdetectionapp.pojo.RegistrationCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.spamdetectionapp.services.UserService;
import com.springboot.spamdetectionapp.utils.userUtils.UserUtils;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<String> register(RegistrationCredentials registrationCredentials){

        try {
            return userService.register(registrationCredentials);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(LoginCredentials loginCredentials) {

        try {
            return userService.login(loginCredentials);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
