package com.springboot.spamdetectionapp.utils.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class HashedPassword {
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    public String generateHashedPassword(String plainPassword) {
        PasswordEncoder passwordEncoder = passwordEncoder();

        return passwordEncoder.encode(plainPassword);
    }

    public Boolean checkPassword(String userPassword,String encodedPassword)
    {
        if(userPassword.length()==0)
        {
            userPassword="99191919";
        }
        PasswordEncoder passwordEncoder = passwordEncoder();

        return passwordEncoder.matches(userPassword,encodedPassword);
    }

}
