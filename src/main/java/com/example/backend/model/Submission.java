package com.example.backend.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Submission {
    private Long submissionId;
    private Long assignmentId;
    private Long studentId;
    private String submissionText;
    private String submissionFileUrl;
    private Integer grade;
    private String feedback;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
