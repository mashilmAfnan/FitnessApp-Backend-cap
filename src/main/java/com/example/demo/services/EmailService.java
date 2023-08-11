package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService implements EmailServiceInterface {
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendRegistrationConfirmationEmail(String to, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Registration Confirmation");
        message.setText("Hello " + username + ",\n\nThank you for registering on our website!");

        mailSender.send(message);
    }
}
