package com.example.backend.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Video {
    private Long videoId;
    private Long courseId;
    private String title;
    private String description;
    private String url;
    private Double rating;
    private Long uploadedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
