package com.example.backend.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class ForumReply {
    private Long replyId;
    private Long topicId;
    private Long userId;
    private String replyText;
    private Timestamp createdAt;
}
