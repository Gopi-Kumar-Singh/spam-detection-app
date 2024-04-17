package com.springboot.spamdetectionapp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static java.lang.Float.NaN;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class UsersToSend implements Serializable {

    private String userName;

    private String phoneNumber;

    private String email;

    private Float spamLikelihood;

    public UsersToSend(RegisteredUser user, UserSpamScore userSpamScore, String phoneNumberDemandingData) {
        this.phoneNumber = user.getPhoneNumber();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.spamLikelihood = this.calculateSpamLikelihood(userSpamScore);
        if(this.spamLikelihood == NaN){
            this.spamLikelihood=0.0f;
        }
    }

    public UsersToSend(GlobalUser globalUser, UserSpamScore userSpamScore, String phoneNumberDemandingData) {
        this.phoneNumber = globalUser.getPhoneNumber();
        this.userName = globalUser.getUserName();
        this.email = globalUser.getEmail();
        this.spamLikelihood = this.calculateSpamLikelihood(userSpamScore);
        if(this.spamLikelihood == NaN){
            this.spamLikelihood=0.0f;
        }
    }


    private Float calculateSpamLikelihood(UserSpamScore userSpamScore) {
        if (userSpamScore == null) {
            return 0.0f;
        }

        int spamCount = userSpamScore.getSpamCount();

        int notSpamCount = userSpamScore.getNotSpamCount();

        return 1f * spamCount / (spamCount + notSpamCount);
    }

}
