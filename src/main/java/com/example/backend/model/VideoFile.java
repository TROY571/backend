package com.example.backend.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class VideoFile {
    private Long fileId;
    private Long videoId;
    private String fileUrl;
    private String fileName;
    private Timestamp createdAt;
}
