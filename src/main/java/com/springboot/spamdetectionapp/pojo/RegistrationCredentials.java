package com.springboot.spamdetectionapp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationCredentials{

    private String userName;

    private String phoneNumber;

    private String email;

    private String password;

}
