package com.example.backend.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ForumTopicDto {
    private Long topicId;
    private Long majorId;
    private Long userId;
    private String topicTitle;
    private String topicContent;
    private String username;
    private String majorName;
    private Timestamp createdAt;
}
