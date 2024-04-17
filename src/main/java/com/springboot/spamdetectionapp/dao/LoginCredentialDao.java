package com.springboot.spamdetectionapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.spamdetectionapp.pojo.LoginCredentials;

public interface LoginCredentialDao extends JpaRepository<LoginCredentials,String> {

    LoginCredentials findByPhoneNumber(String phoneNumber);

}
