package com.springboot.spamdetectionapp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class Contact implements Serializable {

    private String userName;

    private String phoneNumber;

    private String email;

    public String getUserName() {
        if (this.userName == null) {
            this.userName = "";
        }
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        if (this.phoneNumber == null) {
            this.phoneNumber = "";
        }
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        if (this.email == null) {
            this.email = "";
        }
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
