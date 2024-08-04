package com.example.backend.model;


import lombok.Data;

import java.sql.Timestamp;
@Data
public class Favorite {

    private Long favoriteId;
    private Long userId;
    private Long videoId;
    private Timestamp createdAt;
}