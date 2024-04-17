package com.springboot.spamdetectionapp.services;

import org.springframework.stereotype.Service;

public interface MailService {
    public void sendEmail(String to, String subject, String body);
}
