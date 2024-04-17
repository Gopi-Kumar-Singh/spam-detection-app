package com.springboot.spamdetectionapp.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "registered_users")
@NoArgsConstructor
public class RegisteredUser implements Serializable {

    @Id
    @Column(nullable = false,name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false,name = "user_name")
    private String userName;

    @Column(name = "email_address")
    private String email;

    public RegisteredUser(RegistrationCredentials registrationCredentials)
    {
        this.phoneNumber= registrationCredentials.getPhoneNumber();
        this.userName= registrationCredentials.getUserName();
        this.email= registrationCredentials.getEmail();
    }

}
