package com.example.backend.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class User {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private Long schoolId;
    private Role role;
    private String profileImage;
    private Long majorId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public enum Role {
        Student, Teacher, Administrator
    }
}
