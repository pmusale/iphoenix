package com.phoenix.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class Users {
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public static final String STUDENT_EMAIL = "atharwa@example.com";
    public static final String STUDENT_PASSWORD = "student";
    public static final String ADMIN_EMAIL = "prashant@example.com";
    public static final String ADMIN_PASSWORD = "admin";

    private static User STUDENT = User.createUser(newRandomId(),"atharwa",
                                                     STUDENT_EMAIL,
                                                     PASSWORD_ENCODER.encode(STUDENT_PASSWORD), null, null, null);

    private static User ADMIN = User.createUser(newRandomId(),"prashant",
                                                     ADMIN_EMAIL,
                                                   PASSWORD_ENCODER.encode(ADMIN_PASSWORD), null, null, null);


    public static UUID newRandomId() {
        return UUID.randomUUID();
    }

    public static User newRandomStudent() {
        return newRandomStudent(newRandomId());
    }

    public static User newRandomStudent(UUID userId) {
        String uniqueId = userId.toString().substring(0, 5);
        return User.createUser(userId,"atharwa",
                                  "user-" + uniqueId +
                                          "@example.com",
                                  PASSWORD_ENCODER.encode("user"), null, null, null);
    }

    public static User student() {
        return STUDENT;
    }

    public static User captain() {
        return ADMIN;
    }

    private Users() {
    }
}
