package com.example.backend.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Course {
    private Long courseId;
    private String courseName;
    private String courseDescription;
    private Long majorId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

