package com.example.backend.service;

import com.example.backend.mapper.CourseTeacherMapper;
import com.example.backend.model.CourseTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseTeacherService {
    @Autowired
    private CourseTeacherMapper courseTeacherMapper;

    public CourseTeacher findByCourseTeacherId(Long courseTeacherId) {
        return courseTeacherMapper.findByCourseTeacherId(courseTeacherId);
    }

    public List<CourseTeacher> findByCourseId(Long courseId) {
        return courseTeacherMapper.findByCourseId(courseId);
    }

    public List<CourseTeacher> findByTeacherId(Long teacherId) {
        return courseTeacherMapper.findByTeacherId(teacherId);
    }

    public void insertCourseTeacher(CourseTeacher courseTeacher) {
        courseTeacherMapper.insertCourseTeacher(courseTeacher);
    }

    public void deleteCourseTeacher(Long courseTeacherId) {
        courseTeacherMapper.deleteCourseTeacher(courseTeacherId);
    }

    public void deleteByCourseId(Long courseId) {
        courseTeacherMapper.deleteByCourseId(courseId);
    }

    public void deleteByUserId(Long teacherId) {//teacher_id
        courseTeacherMapper.deleteByTeacherId(teacherId);
    }
}
