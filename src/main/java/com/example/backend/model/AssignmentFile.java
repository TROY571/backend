package com.example.backend.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class AssignmentFile {
    private Long fileId;
    private Long assignmentId;
    private String fileUrl;
    private String fileName;
    private Timestamp createdAt;
}
