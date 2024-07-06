package com.example.backend.controller;

import com.example.backend.model.CourseTeacher;
import com.example.backend.service.CourseTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/course-teachers")
public class CourseTeacherController {
    @Autowired
    private CourseTeacherService courseTeacherService;

    @GetMapping("/{id}")
    public ResponseEntity<CourseTeacher> getCourseTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(courseTeacherService.findByCourseTeacherId(id));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseTeacher>> getCourseTeachersByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseTeacherService.findByCourseId(courseId));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<CourseTeacher>> getCourseTeachersByTeacherId(@PathVariable Long teacherId) {
        return ResponseEntity.ok(courseTeacherService.findByTeacherId(teacherId));
    }

    @PostMapping("/")
    public ResponseEntity<CourseTeacher> createCourseTeacher(@RequestBody CourseTeacher courseTeacher) {
        courseTeacherService.insertCourseTeacher(courseTeacher);
        return ResponseEntity.ok(courseTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseTeacher(@PathVariable Long id) {
        courseTeacherService.deleteCourseTeacher(id);
        return ResponseEntity.ok().build();
    }
}
