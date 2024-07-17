package com.alurachallenge.forohub.security.logeo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenera {

    private String secreto;

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "secreto";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}
