package com.example.rentease.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class creds {

    public static void main(String[] args) {
        String plainTextPassword = "welcome";  // Replace with the actual password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(plainTextPassword);

        System.out.println("Hashed Password: " + hashedPassword);
        //  Use this hashed password in your SQL insert statement.
    }
}