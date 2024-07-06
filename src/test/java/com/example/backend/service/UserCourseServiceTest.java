package com.example.backend.service;

import com.example.backend.mapper.UserCourseMapper;
import com.example.backend.model.UserCourse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserCourseServiceTest {

    @InjectMocks
    private UserCourseService userCourseService;

    @Mock
    private UserCourseMapper userCourseMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByUserCourseId() {
        UserCourse userCourse = new UserCourse();
        userCourse.setUserCourseId(1L);
        when(userCourseMapper.findByUserCourseId(1L)).thenReturn(userCourse);

        UserCourse result = userCourseService.findByUserCourseId(1L);
        assertEquals(1L, result.getUserCourseId());
    }

    @Test
    public void testFindByUserId() {
        List<UserCourse> userCourses = List.of(new UserCourse(), new UserCourse());
        when(userCourseMapper.findByUserId(1L)).thenReturn(userCourses);

        List<UserCourse> result = userCourseService.findByUserId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindByCourseId() {
        List<UserCourse> userCourses = List.of(new UserCourse(), new UserCourse());
        when(userCourseMapper.findByCourseId(1L)).thenReturn(userCourses);

        List<UserCourse> result = userCourseService.findByCourseId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testInsertUserCourse() {
        UserCourse userCourse = new UserCourse();
        userCourseService.insertUserCourse(userCourse);
        verify(userCourseMapper, times(1)).insertUserCourse(userCourse);
    }

    @Test
    public void testDeleteUserCourse() {
        userCourseService.deleteUserCourse(1L);
        verify(userCourseMapper, times(1)).deleteUserCourse(1L);
    }
}
