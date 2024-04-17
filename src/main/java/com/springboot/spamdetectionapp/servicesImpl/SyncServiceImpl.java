package com.springboot.spamdetectionapp.servicesImpl;

import com.springboot.spamdetectionapp.constants.Constants;
import com.springboot.spamdetectionapp.dao.RegisteredUserDao;
import com.springboot.spamdetectionapp.pojo.Contact;
import com.springboot.spamdetectionapp.pojo.RegisteredUser;
import com.springboot.spamdetectionapp.services.SyncService;
import com.springboot.spamdetectionapp.utils.myFunctions.AddToScamScoresTable;
import com.springboot.spamdetectionapp.utils.userUtils.UserUtils;
import com.springboot.spamdetectionapp.utils.security.ValidateUserLogedinOrNot;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SyncServiceImpl implements SyncService {
    @Autowired
    private AddToScamScoresTable addToScamScoresTable;
    @Autowired
    private RegisteredUserDao registeredUserDao;
    @Autowired
    private ValidateUserLogedinOrNot validateUserLogedinOrNot;

    @Override
    public ResponseEntity<String> syncYourContacts(Contact[] myContacts, String token) {

        try {

            String loginUsersPhoneNumber = UserUtils.getPhoneNumber(token);

            if (token.isEmpty() || !validateUserLogedinOrNot.validateUser(token)) {
                return UserUtils.getResponseEntity(Constants.PLEASE_LOGIN, HttpStatus.UNAUTHORIZED);
            }

            RegisteredUser user = registeredUserDao.findByPhoneNumber(loginUsersPhoneNumber);
            for (int i = 0; i < myContacts.length; i++) {
                if(myContacts[i].getPhoneNumber().isEmpty()){
                    continue;
                }
                addToScamScoresTable.addToUserSpamScores(myContacts[i], 0, 0, user);
            }
            return UserUtils.getResponseEntity(Constants.CONTACTS_SYNC_SUCCESSFULLY, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
