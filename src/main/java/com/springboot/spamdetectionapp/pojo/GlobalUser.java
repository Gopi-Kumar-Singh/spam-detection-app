package com.springboot.spamdetectionapp.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@Entity
@Table(name = "global_users")
@NoArgsConstructor
public class GlobalUser implements Serializable {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false, name = "user_name")
    private String userName;

    @Column(name = "email_address")
    private String email;

    @ManyToOne
    @JoinColumn(name = "added_to_db_by_phone_number", referencedColumnName = "phone_number")
    private RegisteredUser addedToByRegisteredUser;

    public GlobalUser(String phoneNumber, RegisteredUser registeredUser) {
        this.phoneNumber = phoneNumber;
        this.userName = phoneNumber;
        this.email="";
        this.addedToByRegisteredUser = registeredUser;
    }

    public GlobalUser(String userName, String phoneNumber, String email, RegisteredUser registeredUser) {
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.email = email;
        this.addedToByRegisteredUser = registeredUser;
    }


}
