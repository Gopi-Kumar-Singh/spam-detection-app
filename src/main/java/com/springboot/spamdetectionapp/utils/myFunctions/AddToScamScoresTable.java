package com.springboot.spamdetectionapp.utils.myFunctions;

import com.springboot.spamdetectionapp.dao.GlobalUserDao;
import com.springboot.spamdetectionapp.dao.UserSpamScoreDao;
import com.springboot.spamdetectionapp.pojo.Contact;
import com.springboot.spamdetectionapp.pojo.GlobalUser;
import com.springboot.spamdetectionapp.pojo.RegisteredUser;
import com.springboot.spamdetectionapp.pojo.UserSpamScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddToScamScoresTable {

    @Autowired
    private UserSpamScoreDao userSpamScoreDao;

    @Autowired
    private GlobalUserDao globalUserDao;

    public void addToUserSpamScores(String phoneNumber, int spamAdd, int notSpamAdd, RegisteredUser registeredUser) {
        try {
            UserSpamScore userSpamScore = userSpamScoreDao.findByPhoneNumber(phoneNumber);

            if (userSpamScore != null) {
                userSpamScore.setSpamCount(userSpamScore.getSpamCount() + spamAdd);
                userSpamScore.setNotSpamCount(userSpamScore.getNotSpamCount() + notSpamAdd);
                userSpamScoreDao.save(userSpamScore);
                return;
            }

            GlobalUser globalUser = new GlobalUser(phoneNumber, registeredUser);
            globalUserDao.save(globalUser);

            UserSpamScore newUser = new UserSpamScore(phoneNumber);
            newUser.setSpamCount(spamAdd);
            newUser.setNotSpamCount(notSpamAdd);
            userSpamScoreDao.save(newUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addToUserSpamScores(Contact contact, int spamAdd, int notSpamAdd, RegisteredUser registeredUser) {
        try {

            String phoneNumber = contact.getPhoneNumber();
            String userName = contact.getUserName();
            String email = contact.getEmail();

            if (userName.isEmpty()) {
                userName = phoneNumber;
            }

            UserSpamScore userSpamScore = userSpamScoreDao.findByPhoneNumber(phoneNumber);

            if (userSpamScore != null) {
                userSpamScore.setSpamCount(userSpamScore.getSpamCount() + spamAdd);
                userSpamScore.setNotSpamCount(userSpamScore.getNotSpamCount() + notSpamAdd);
                userSpamScoreDao.save(userSpamScore);
                return;
            }

            GlobalUser globalUser = new GlobalUser(userName, phoneNumber, email, registeredUser);
            globalUserDao.save(globalUser);

            UserSpamScore newUser = new UserSpamScore(phoneNumber);
            newUser.setSpamCount(spamAdd);
            newUser.setNotSpamCount(notSpamAdd);
            userSpamScoreDao.save(newUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
