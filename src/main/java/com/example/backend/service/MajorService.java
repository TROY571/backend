package com.example.backend.service;

import com.example.backend.mapper.*;
import com.example.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MajorService {

    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserCourseMapper userCourseMapper;
    @Autowired
    private CourseTeacherMapper courseTeacherMapper;
    @Autowired
    private VideoService videoService;
    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private ForumTopicService forumTopicService;

    public Major findByMajorId(Long majorId) {
        return majorMapper.findByMajorId(majorId);
    }

    public List<Major> findAllMajors() {
        return majorMapper.findAllMajors();
    }

    public Major insertMajor(Major major) {
        major.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        major.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        majorMapper.insertMajor(major);
        return  major;
    }

    public void updateMajor(Major major) {
        major.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        majorMapper.updateMajor(major);
    }

    public void deleteMajor(Long majorId) {
        List<Course> courses = courseMapper.findByMajorId(majorId);
        for (Course course : courses) {
            courseTeacherMapper.deleteByCourseId(course.getCourseId());
            userCourseMapper.deleteByCourseId(course.getCourseId());
            videoService.deleteByCourseId(course.getCourseId());
            assignmentService.deleteByCourseId(course.getCourseId());

            courseMapper.deleteCourse(course.getCourseId());
        }

        List<User> users = userMapper.findByMajorId(majorId);
        for (User user : users) {
            user.setMajorId(null);
            userMapper.updateUser(user);
        }
        forumTopicService.deleteByMajorId(majorId);
        majorMapper.deleteMajor(majorId);
    }

    public Major findByMajorName(String majorName) {
        return majorMapper.findByMajorName(majorName);
    }
}
