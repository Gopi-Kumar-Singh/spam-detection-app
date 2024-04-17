package com.springboot.spamdetectionapp.servicesImpl;

import com.springboot.spamdetectionapp.constants.Constants;
import com.springboot.spamdetectionapp.dao.LoginCredentialDao;
import com.springboot.spamdetectionapp.pojo.*;
import com.springboot.spamdetectionapp.services.UserService;
import com.springboot.spamdetectionapp.utils.myFunctions.AddToScamScoresTable;
import com.springboot.spamdetectionapp.utils.security.HashedPassword;
import com.springboot.spamdetectionapp.utils.userUtils.UserUtils;
import com.springboot.spamdetectionapp.utils.security.Validations;
import com.springboot.spamdetectionapp.dao.RegisteredUserDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private LoginCredentialDao loginCredentialDao;
    @Autowired
    private RegisteredUserDao registeredUserDao;
    @Autowired
    private HashedPassword hashedPassword;
    @Autowired
    private AddToScamScoresTable addToScamScoresTable;

    @Override
    public ResponseEntity<String> register(RegistrationCredentials registrationCredentials) {

        try {

            if (!Validations.validateUserName(registrationCredentials.getUserName()) || !Validations.validatePhoneNumber(registrationCredentials.getPhoneNumber()) || !Validations.validatePassword(registrationCredentials.getPassword()) || !Validations.validateEmail(registrationCredentials.getEmail())) {
                return UserUtils.getResponseEntity(Constants.INVALID_CREDENTIAL, HttpStatus.BAD_REQUEST);
            }

            // Hashing password entered by user
            registrationCredentials.setPassword(hashedPassword.generateHashedPassword(registrationCredentials.getPassword()));

            //Checking if phone number is already used or not
            LoginCredentials loginCredentials = loginCredentialDao.findByPhoneNumber(registrationCredentials.getPhoneNumber());

            if (loginCredentials != null) {
                return UserUtils.getResponseEntity(Constants.EXISTING_PHONE_NUMBER, HttpStatus.BAD_REQUEST);
            }

            RegisteredUser registeredUser = new RegisteredUser(registrationCredentials);
            registeredUserDao.save(registeredUser);
            loginCredentialDao.save(new LoginCredentials(registrationCredentials));

            addToScamScoresTable.addToUserSpamScores(registrationCredentials.getPhoneNumber(), 0, 0, registeredUser);

            return UserUtils.getResponseEntity(Constants.USER_REGISTERED_SUCCESSFULLY, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(LoginCredentials loginCredentials) {

        try {
            if (!Validations.validatePhoneNumber(loginCredentials.getPhoneNumber()) || !Validations.validatePassword(loginCredentials.getPassword())) {
                return UserUtils.getResponseEntity(Constants.INVALID_CREDENTIAL, HttpStatus.BAD_REQUEST);
            }

            LoginCredentials loginCredentialCheck = loginCredentialDao.findByPhoneNumber(loginCredentials.getPhoneNumber());

            if (loginCredentialCheck != null && hashedPassword.checkPassword(loginCredentials.getPassword(), loginCredentialCheck.getPassword())) {

                // Creating token and attaching it in Response header
                String token = loginCredentials.getPhoneNumber();
                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", "Bearer " + token);
                return new ResponseEntity<>(Constants.USER_LOGIN_SUCCESSFULLY, headers, HttpStatus.ACCEPTED);
            }

            return UserUtils.getResponseEntity(Constants.INVALID_CREDENTIAL, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
