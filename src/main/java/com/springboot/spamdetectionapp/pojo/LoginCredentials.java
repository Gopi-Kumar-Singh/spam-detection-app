package com.springboot.spamdetectionapp.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_login_credentials")
public class LoginCredentials implements Serializable {

    @Id
    @Column(nullable = false,name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    public LoginCredentials(RegistrationCredentials registrationCredentials)
    {
        this.password=registrationCredentials.getPassword();
        this.phoneNumber=registrationCredentials.getPhoneNumber();
    }

    public LoginCredentials(String phoneNumber)
    {
        this.phoneNumber=phoneNumber;
        this.password="";
    }

    public LoginCredentials(Map<String,String>mp)
    {
        this.phoneNumber=mp.get("phoneNumber");
        this.password=mp.get("password");
    }


}
