package com.springboot.spamdetectionapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.spamdetectionapp.pojo.GlobalUser;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GlobalUserDao extends JpaRepository<GlobalUser, Integer> {

    List<GlobalUser> findByPhoneNumber(String phoneNumber);

    @Query("SELECT u FROM GlobalUser u WHERE lower(u.userName) LIKE lower(concat(:name, '%')) ORDER BY u.userName")
    List<GlobalUser> findUsersWhoseNameStartsGivenName(String name);

    @Query("SELECT u FROM GlobalUser u WHERE lower(u.userName) LIKE lower(concat('%', :name, '%')) AND lower(u.userName) NOT LIKE lower(concat(:name, '%')) ORDER BY u.userName")
    List<GlobalUser> findUsersWhoseNameContainsGivenNameButNotStartsWithIt(String name);
}
