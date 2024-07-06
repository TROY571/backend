package com.example.backend.controller;

import com.example.backend.model.UserCourse;
import com.example.backend.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user-courses")
public class UserCourseController {
    @Autowired
    private UserCourseService userCourseService;

    @GetMapping("/{id}")
    public ResponseEntity<UserCourse> getUserCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(userCourseService.findByUserCourseId(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserCourse>> getUserCoursesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userCourseService.findByUserId(userId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<UserCourse>> getUserCoursesByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(userCourseService.findByCourseId(courseId));
    }

    @PostMapping("/")
    public ResponseEntity<UserCourse> createUserCourse(@RequestBody UserCourse userCourse) {
        userCourseService.insertUserCourse(userCourse);
        return ResponseEntity.ok(userCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserCourse(@PathVariable Long id) {
        userCourseService.deleteUserCourse(id);
        return ResponseEntity.ok().build();
    }
}
