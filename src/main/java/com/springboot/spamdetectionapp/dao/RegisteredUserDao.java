package com.springboot.spamdetectionapp.dao;

import com.springboot.spamdetectionapp.pojo.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredUserDao extends JpaRepository<RegisteredUser,String>{
    RegisteredUser findByPhoneNumber(String phoneNumber);
}
