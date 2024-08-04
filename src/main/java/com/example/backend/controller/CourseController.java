package com.example.backend.controller;

import com.example.backend.model.Course;
import com.example.backend.service.CourseService;
import com.example.backend.service.CourseTeacherService;
import com.example.backend.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserCourseService userCourseService;
    @Autowired
    private CourseTeacherService courseTeacherService;
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findByCourseId(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.findAllCourses());
    }

    @GetMapping("/major/{majorId}")
    public ResponseEntity<List<Course>> getCoursesByMajorId(@PathVariable Long majorId) {
        return ResponseEntity.ok(courseService.findByMajorId(majorId));
    }

    @GetMapping("/major/{majorId}/{teacherId}")
    public ResponseEntity<List<Course>> getCoursesByMajorIdAndTeacherId(@PathVariable Long majorId,@PathVariable Long teacherId) {
        return ResponseEntity.ok(courseService.findByMajorIdAndTeacherId(majorId,teacherId));
    }
    @GetMapping("/major/student/{majorId}/{studentId}")
    public ResponseEntity<List<Course>> getCoursesByMajorIdAndStudentId(@PathVariable Long majorId,@PathVariable Long studentId) {
        return ResponseEntity.ok(courseService.findByMajorIdAndStudentId(majorId,studentId));
    }
    @PostMapping("/")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        course.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        course.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        courseService.insertCourse(course);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Course byCourseId = courseService.findByCourseId(id);
        byCourseId.setCourseName(course.getCourseName());
        byCourseId.setCourseDescription(course.getCourseDescription());
        byCourseId.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        courseService.updateCourse(byCourseId);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseTeacherService.deleteByCourseId(id);
        userCourseService.deleteByCourseId(id);
        courseService.deleteCourse(id);

        return ResponseEntity.ok().build();
    }
}
