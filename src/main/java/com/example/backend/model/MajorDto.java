package com.example.backend.model;

import lombok.Data;

@Data
public class MajorDto {

    private Major major;
    private String majorName;
    private Long teacherId;
}
