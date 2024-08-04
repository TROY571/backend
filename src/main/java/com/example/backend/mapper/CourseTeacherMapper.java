package com.example.backend.mapper;

import com.example.backend.model.CourseTeacher;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CourseTeacherMapper {
    CourseTeacher findByCourseTeacherId(Long courseTeacherId);

    List<CourseTeacher> findByCourseId(Long courseId);

    List<CourseTeacher> findByTeacherId(Long teacherId);

    void insertCourseTeacher(CourseTeacher courseTeacher);

    void deleteCourseTeacher(Long courseTeacherId);

    void deleteByCourseId(Long courseId);

    void deleteByTeacherId(Long teacherId);
}
