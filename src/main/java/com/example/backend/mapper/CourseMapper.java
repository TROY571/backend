package com.example.backend.mapper;

import com.example.backend.model.Course;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CourseMapper {
    Course findByCourseId(Long courseId);

    List<Course> findByMajorId(Long majorId);

    List<Course> findAllCourses();

    void insertCourse(Course course);

    void updateCourse(Course course);

    void deleteCourse(Long courseId);
}
