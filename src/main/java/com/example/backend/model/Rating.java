package com.example.backend.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Rating {
    private Long ratingId;
    private Long videoId;
    private Long userId;
    private Integer rating;
    private Timestamp createdAt;
}
