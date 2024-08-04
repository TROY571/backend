package com.example.backend.model;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class FavoriteDto {

    private Long favoriteId;
    private Long userId;
    private Long videoId;
    private String title;
    private String description;
    private String url;
    private Timestamp createdAt;
}