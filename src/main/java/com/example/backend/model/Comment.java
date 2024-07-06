package com.example.backend.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Comment {
    private Long commentId;
    private Long videoId;
    private Long userId;
    private String commentText;
    private Long parentCommentId;
    private Timestamp createdAt;
}
