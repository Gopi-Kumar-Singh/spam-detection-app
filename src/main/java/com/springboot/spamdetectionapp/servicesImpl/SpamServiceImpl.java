package com.springboot.spamdetectionapp.servicesImpl;

import com.springboot.spamdetectionapp.constants.Constants;
import com.springboot.spamdetectionapp.dao.RegisteredUserDao;
import com.springboot.spamdetectionapp.dao.UserSpamScoreDao;
import com.springboot.spamdetectionapp.pojo.RegisteredUser;
import com.springboot.spamdetectionapp.pojo.UserSpamScore;
import com.springboot.spamdetectionapp.utils.myFunctions.AddToScamScoresTable;
import com.springboot.spamdetectionapp.utils.userUtils.UserUtils;
import com.springboot.spamdetectionapp.utils.security.ValidateUserLogedinOrNot;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.springboot.spamdetectionapp.services.SpamService;

@Service
@Transactional
public class SpamServiceImpl implements SpamService {

    @Autowired
    UserSpamScoreDao userSpamScoreDao;

    @Autowired
    AddToScamScoresTable addToScamScoresTable;

    @Autowired
    RegisteredUserDao registeredUserDao;
    @Autowired
    private ValidateUserLogedinOrNot validateUserLogedinOrNot;

    @Override
    public ResponseEntity<String> reportNumberAsSpam(String spamPhoneNumber, String token) {

        try {

            String loginUsersPhoneNumber = UserUtils.getPhoneNumber(token);

            if (token.isEmpty() || !validateUserLogedinOrNot.validateUser(token)) {
                return UserUtils.getResponseEntity(Constants.PLEASE_LOGIN, HttpStatus.UNAUTHORIZED);
            }

            RegisteredUser registeredUser = registeredUserDao.findByPhoneNumber(loginUsersPhoneNumber);

            addToScamScoresTable.addToUserSpamScores(spamPhoneNumber, 1, 0, registeredUser);

            return UserUtils.getResponseEntity(Constants.REPORTED_SPAM, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> reportNumberAsNotSpam(String notSpamPhoneNumber, String token) {

        try {

            String loginUsersPhoneNumber = UserUtils.getPhoneNumber(token);

            if (token.isEmpty() || !validateUserLogedinOrNot.validateUser(token)) {
                return UserUtils.getResponseEntity(Constants.PLEASE_LOGIN, HttpStatus.UNAUTHORIZED);
            }

            RegisteredUser registeredUser = registeredUserDao.findByPhoneNumber(loginUsersPhoneNumber);
            addToScamScoresTable.addToUserSpamScores(notSpamPhoneNumber, 0, 1, registeredUser);

            return UserUtils.getResponseEntity(Constants.REPORTED_NOT_SPAM, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> checkNumberForSpam(String checkPhoneNumber, String token) {

        try {

            String loginUsersPhoneNumber = UserUtils.getPhoneNumber(token);

            if (token.isEmpty() || !validateUserLogedinOrNot.validateUser(token)) {
                return UserUtils.getResponseEntity(Constants.PLEASE_LOGIN, HttpStatus.UNAUTHORIZED);
            }

            RegisteredUser registeredUser = registeredUserDao.findByPhoneNumber(loginUsersPhoneNumber);
            addToScamScoresTable.addToUserSpamScores(checkPhoneNumber, 0, 0, registeredUser);

            UserSpamScore userSpamScore = userSpamScoreDao.findByPhoneNumber(checkPhoneNumber);
            return new ResponseEntity<>(userSpamScore, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
