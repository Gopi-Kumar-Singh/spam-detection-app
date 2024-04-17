package com.springboot.spamdetectionapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.spamdetectionapp.pojo.UserSpamScore;

public interface UserSpamScoreDao extends JpaRepository<UserSpamScore,String> {

    UserSpamScore findByPhoneNumber(String phoneNumber);
}
