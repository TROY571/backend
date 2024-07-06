package com.example.backend.service;

import com.example.backend.mapper.UserCourseMapper;
import com.example.backend.model.UserCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserCourseService {
    @Autowired
    private UserCourseMapper userCourseMapper;

    public UserCourse findByUserCourseId(Long userCourseId) {
        return userCourseMapper.findByUserCourseId(userCourseId);
    }

    public List<UserCourse> findByUserId(Long userId) {
        return userCourseMapper.findByUserId(userId);
    }

    public List<UserCourse> findByCourseId(Long courseId) {
        return userCourseMapper.findByCourseId(courseId);
    }

    public void insertUserCourse(UserCourse userCourse) {
        userCourseMapper.insertUserCourse(userCourse);
    }

    public void deleteUserCourse(Long userCourseId) {
        userCourseMapper.deleteUserCourse(userCourseId);
    }
}
