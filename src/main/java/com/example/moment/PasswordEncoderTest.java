package com.example.moment;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "8765"; // 원하는 비밀번호로 변경 가능
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("암호화된 비밀번호: " + encodedPassword);
    }
}
