package com.example.backend.mapper;

import com.example.backend.model.UserCourse;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserCourseMapper {
    UserCourse findByUserCourseId(Long userCourseId);

    List<UserCourse> findByUserId(Long userId);

    List<UserCourse> findByCourseId(Long courseId);

    void insertUserCourse(UserCourse userCourse);

    void deleteUserCourse(Long userCourseId);
}
