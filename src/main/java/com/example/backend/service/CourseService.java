package com.example.backend.service;

import com.example.backend.mapper.CourseMapper;
import com.example.backend.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    public Course findByCourseId(Long courseId) {
        return courseMapper.findByCourseId(courseId);
    }

    public List<Course> findByMajorId(Long majorId) {
        return courseMapper.findByMajorId(majorId);
    }

    public List<Course> findAllCourses() {
        return courseMapper.findAllCourses();
    }

    public void insertCourse(Course course) {
        course.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        course.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        courseMapper.insertCourse(course);
    }

    public void updateCourse(Course course) {
        course.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        courseMapper.updateCourse(course);
    }

    public void deleteCourse(Long courseId) {
        courseMapper.deleteCourse(courseId);
    }
}
