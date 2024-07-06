package com.example.backend.model;

import lombok.Data;

@Data
public class CourseTeacher {
    private Long courseTeacherId;
    private Long courseId;
    private Long teacherId;
}
