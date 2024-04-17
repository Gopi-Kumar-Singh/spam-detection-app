package com.springboot.spamdetectionapp.servicesImpl;

import com.springboot.spamdetectionapp.constants.Constants;
import com.springboot.spamdetectionapp.dao.GlobalUserDao;
import com.springboot.spamdetectionapp.dao.RegisteredUserDao;
import com.springboot.spamdetectionapp.dao.UserSpamScoreDao;
import com.springboot.spamdetectionapp.pojo.GlobalUser;
import com.springboot.spamdetectionapp.pojo.RegisteredUser;
import com.springboot.spamdetectionapp.pojo.UserSpamScore;
import com.springboot.spamdetectionapp.pojo.UsersToSend;
import com.springboot.spamdetectionapp.services.SearchService;
import com.springboot.spamdetectionapp.utils.userUtils.UserUtils;
import com.springboot.spamdetectionapp.utils.security.ValidateUserLogedinOrNot;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

    @Autowired
    private GlobalUserDao globalUserDao;
    @Autowired
    private UserSpamScoreDao userSpamScoreDao;
    @Autowired
    private RegisteredUserDao registeredUserDao;
    @Autowired
    private ValidateUserLogedinOrNot validateUserLogedinOrNot ;

    @Override
    public ResponseEntity<?> searchByName(String name, String token) {

        try {

            String loginUsersPhoneNumber = UserUtils.getPhoneNumber(token);

            if (token.isEmpty() || !validateUserLogedinOrNot.validateUser(token)) {
                return UserUtils.getResponseEntity(Constants.PLEASE_LOGIN, HttpStatus.UNAUTHORIZED);
            }

            List<GlobalUser> startingWithName = globalUserDao.findUsersWhoseNameStartsGivenName(name);

            List<GlobalUser> containingTheName = globalUserDao.findUsersWhoseNameContainsGivenNameButNotStartsWithIt(name);

            List<GlobalUser> completeUsers = Stream.concat(startingWithName.stream(), containingTheName.stream()).toList();

            List<UsersToSend> finalUsers = new ArrayList<>();

            for (int i = 0; i < completeUsers.size(); i++) {
                UserSpamScore userSpamScore = userSpamScoreDao.findByPhoneNumber(completeUsers.get(i).getPhoneNumber());
                UsersToSend user = new UsersToSend(completeUsers.get(i), userSpamScore, loginUsersPhoneNumber);
                finalUsers.add(user);
            }

            if (finalUsers.isEmpty()) {
                return UserUtils.getResponseEntity(Constants.NO_USER_FOUND, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(finalUsers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> searchByPhoneNumber(String phoneNumber, String token) {

        try {

            String loginUsersPhoneNumber = UserUtils.getPhoneNumber(token);

            if (token.isEmpty() || !validateUserLogedinOrNot.validateUser(token)) {
                return UserUtils.getResponseEntity(Constants.PLEASE_LOGIN, HttpStatus.UNAUTHORIZED);
            }

            RegisteredUser registeredUser = registeredUserDao.findByPhoneNumber(phoneNumber);

            if (registeredUser != null) {
                UserSpamScore userSpamScore = userSpamScoreDao.findByPhoneNumber(registeredUser.getPhoneNumber());
                UsersToSend usersToSend = new UsersToSend(registeredUser, userSpamScore, loginUsersPhoneNumber);
                return new ResponseEntity<>(usersToSend, HttpStatus.OK);
            }

            List<GlobalUser> completeUsers = globalUserDao.findByPhoneNumber(phoneNumber);

            if (completeUsers.isEmpty()) {
                return UserUtils.getResponseEntity(Constants.NO_USER_FOUND, HttpStatus.NO_CONTENT);
            }

            List<UsersToSend> finalUsers = new ArrayList<>();

            for (int i = 0; i < completeUsers.size(); i++) {
                UserSpamScore userSpamScore = userSpamScoreDao.findByPhoneNumber(completeUsers.get(i).getPhoneNumber());
                UsersToSend user = new UsersToSend(completeUsers.get(i), userSpamScore, loginUsersPhoneNumber);
                finalUsers.add(user);
            }

            return new ResponseEntity<>(finalUsers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
