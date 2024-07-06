package com.example.backend.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Assignment {
    private Long assignmentId;
    private Long courseId;
    private String title;
    private String description;
    private Timestamp dueDate;
    private Long createdBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
