package com.example.backend.service;

import com.example.backend.mapper.CourseTeacherMapper;
import com.example.backend.model.CourseTeacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CourseTeacherServiceTest {

    @InjectMocks
    private CourseTeacherService courseTeacherService;

    @Mock
    private CourseTeacherMapper courseTeacherMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByCourseTeacherId() {
        CourseTeacher courseTeacher = new CourseTeacher();
        courseTeacher.setCourseTeacherId(1L);
        when(courseTeacherMapper.findByCourseTeacherId(1L)).thenReturn(courseTeacher);

        CourseTeacher result = courseTeacherService.findByCourseTeacherId(1L);
        assertEquals(1L, result.getCourseTeacherId());
    }

    @Test
    public void testFindByCourseId() {
        List<CourseTeacher> courseTeachers = List.of(new CourseTeacher(), new CourseTeacher());
        when(courseTeacherMapper.findByCourseId(1L)).thenReturn(courseTeachers);

        List<CourseTeacher> result = courseTeacherService.findByCourseId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindByTeacherId() {
        List<CourseTeacher> courseTeachers = List.of(new CourseTeacher(), new CourseTeacher());
        when(courseTeacherMapper.findByTeacherId(1L)).thenReturn(courseTeachers);

        List<CourseTeacher> result = courseTeacherService.findByTeacherId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testInsertCourseTeacher() {
        CourseTeacher courseTeacher = new CourseTeacher();
        courseTeacherService.insertCourseTeacher(courseTeacher);
        verify(courseTeacherMapper, times(1)).insertCourseTeacher(courseTeacher);
    }

    @Test
    public void testDeleteCourseTeacher() {
        courseTeacherService.deleteCourseTeacher(1L);
        verify(courseTeacherMapper, times(1)).deleteCourseTeacher(1L);
    }
}
