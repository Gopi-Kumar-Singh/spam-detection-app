package com.springboot.spamdetectionapp.utils.security;

import com.springboot.spamdetectionapp.dao.LoginCredentialDao;
import com.springboot.spamdetectionapp.pojo.LoginCredentials;
import com.springboot.spamdetectionapp.utils.userUtils.UserUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class ValidateUserLogedinOrNot {

    @Autowired
    private LoginCredentialDao loginCredentialDao;

    public Boolean validateUser(String token) {

        String phoneNumber = UserUtils.getPhoneNumber(token);

        if (phoneNumber.isEmpty()) {
            return false;
        }

        LoginCredentials loginCredentials = loginCredentialDao.findByPhoneNumber(phoneNumber);

        if (loginCredentials == null) {
            return false;
        }

        return true;
    }
}
