package com.example.backend.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Major {
    private Long majorId;
    private String majorName;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

