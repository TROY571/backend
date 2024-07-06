package com.example.backend.model;

import lombok.Data;

@Data
public class UserCourse {
    private Long userCourseId;
    private Long userId;
    private Long courseId;
}
