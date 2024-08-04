package com.example.backend.mapper;

import com.example.backend.model.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {
    Course findByCourseId(Long courseId);

    List<Course> findByMajorId(Long majorId);

    List<Course> findAllCourses();

    void insertCourse(Course course);

    void updateCourse(Course course);

    void deleteCourse(Long courseId);

    List<Course> findByMajorIdAndTeacherId(@Param("majorId") Long majorId, @Param("teacherId") Long teacherId);

    List<Course> findByMajorIdAndStudentId(@Param("majorId") Long majorId,@Param("studentId")  Long studentId);


}
