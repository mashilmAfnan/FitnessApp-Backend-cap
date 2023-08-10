package com.example.demo.services;

public interface EmailServiceInterface {
    void sendRegistrationConfirmationEmail(String to, String username);
}
