package com.example.backend.service;

import com.example.backend.mapper.UserMapper;
import com.example.backend.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        userService.saveUser(user);
        verify(userMapper, times(1)).insertUser(user);
    }

    @Test
    public void testFindBySchoolId() {
        User user = new User();
        user.setSchoolId(1L);
        when(userMapper.findBySchoolId(1L)).thenReturn(user);

        User result = userService.findBySchoolId(1L);
        assertEquals(1L, result.getSchoolId());
    }

    @Test
    public void testFindByUserId() {
        User user = new User();
        user.setUserId(1L);
        when(userMapper.findByUserId(1L)).thenReturn(user);

        User result = userService.findByUserId(1L);
        assertEquals(1L, result.getUserId());
    }

    @Test
    public void testFindByMajorId() {
        List<User> users = List.of(new User(), new User());
        when(userMapper.findByMajorId(1L)).thenReturn(users);

        List<User> result = userService.findByMajorId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindByRole() {
        List<User> users = List.of(new User(), new User());
        when(userMapper.findByRole(User.Role.student)).thenReturn(users);

        List<User> result = userService.findByRole(User.Role.student);
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        userService.updateUser(user);
        verify(userMapper, times(1)).updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(1L);
        verify(userMapper, times(1)).deleteUser(1L);
    }

    @Test
    public void testFindAllUsers() {
        List<User> users = List.of(new User(), new User());
        when(userMapper.findAllUsers()).thenReturn(users);

        List<User> result = userService.findAllUsers();
        assertEquals(2, result.size());
    }

    @Test
    public void testLogin() {
        User user = new User();
        user.setSchoolId(1L);
        user.setPassword("password");
        when(userMapper.findBySchoolId(1L)).thenReturn(user);

        User result = userService.login(1L, "password");
        assertEquals(user, result);

        result = userService.login(1L, "wrongpassword");
        assertNull(result);

        when(userMapper.findBySchoolId(1L)).thenReturn(null);
        result = userService.login(1L, "password");
        assertNull(result);
    }

    @Test
    public void testResetUsername() {
        User user = new User();
        user.setUserId(1L);
        when(userMapper.findByUserId(1L)).thenReturn(user);

        userService.resetUsername(1L, "newUsername");
        assertEquals("newUsername", user.getUsername());
        verify(userMapper, times(1)).updateUser(user);
    }

    @Test
    public void testResetPassword() {
        User user = new User();
        user.setUserId(1L);
        when(userMapper.findByUserId(1L)).thenReturn(user);

        userService.resetPassword(1L, "newPassword");
        assertEquals("newPassword", user.getPassword());
        verify(userMapper, times(1)).updateUser(user);
    }

    @Test
    public void testResetProfileImage() {
        User user = new User();
        user.setUserId(1L);
        when(userMapper.findByUserId(1L)).thenReturn(user);

        userService.resetProfileImage(1L, "newProfileImage");
        assertEquals("newProfileImage", user.getProfileImage());
        verify(userMapper, times(1)).updateUser(user);
    }
}
