package com.springboot.spamdetectionapp.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static java.lang.Float.NaN;

@Entity
@Table(name = "user_spam_scores")
@AllArgsConstructor
public class UserSpamScore implements Serializable {

    @Id
    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    @Column(name = "spam_count", columnDefinition = "integer default 0")
    private int spamCount;
    @Column(name = "not_spam_count", columnDefinition = "integer default 0")
    private int notSpamCount;

    public UserSpamScore() {
        this.phoneNumber = "";
        this.spamCount = 0;
        this.notSpamCount = 0;
    }

    public UserSpamScore(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.spamCount = 0;
        this.notSpamCount = 0;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSpamCount() {
        if (this.spamCount == NaN) {
            this.spamCount = 0;
        }
        return spamCount;
    }

    public void setSpamCount(int spamCount) {
        this.spamCount = spamCount;
    }

    public int getNotSpamCount() {
        if (this.notSpamCount == NaN) {
            this.notSpamCount = 0;
        }
        return notSpamCount;
    }

    public void setNotSpamCount(int notSpamCount) {
        this.notSpamCount = notSpamCount;
    }
}
