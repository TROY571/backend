package com.example.backend.service;

import com.example.backend.mapper.CourseMapper;
import com.example.backend.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseMapper courseMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByCourseId() {
        Course course = new Course();
        course.setCourseId(1L);
        when(courseMapper.findByCourseId(1L)).thenReturn(course);

        Course result = courseService.findByCourseId(1L);
        assertEquals(1L, result.getCourseId());
    }

    @Test
    public void testFindByMajorId() {
        List<Course> courses = List.of(new Course(), new Course());
        when(courseMapper.findByMajorId(1L)).thenReturn(courses);

        List<Course> result = courseService.findByMajorId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindAllCourses() {
        List<Course> courses = List.of(new Course(), new Course());
        when(courseMapper.findAllCourses()).thenReturn(courses);

        List<Course> result = courseService.findAllCourses();
        assertEquals(2, result.size());
    }

    @Test
    public void testInsertCourse() {
        Course course = new Course();
        courseService.insertCourse(course);
        verify(courseMapper, times(1)).insertCourse(course);
    }

    @Test
    public void testUpdateCourse() {
        Course course = new Course();
        courseService.updateCourse(course);
        verify(courseMapper, times(1)).updateCourse(course);
    }

    @Test
    public void testDeleteCourse() {
        courseService.deleteCourse(1L);
        verify(courseMapper, times(1)).deleteCourse(1L);
    }
}
