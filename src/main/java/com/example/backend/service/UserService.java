package com.example.backend.service;

import com.example.backend.mapper.UserMapper;
import com.example.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void saveUser(User user) {
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userMapper.insertUser(user);
    }

    public User findBySchoolId(Long schoolId) {
        return userMapper.findBySchoolId(schoolId);
    }

    public User findByUserId(Long userId) {
        return userMapper.findByUserId(userId);
    }

    public List<User> findByMajorId(Long majorId) {
        return userMapper.findByMajorId(majorId);
    }

    public List<User> findByRole(User.Role role) {
        return userMapper.findByRole(role);
    }

    public void updateUser(User user) {
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userMapper.updateUser(user);
    }

    public void deleteUser(Long userId) {
        userMapper.deleteUser(userId);
    }

    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    public User login(Long schoolId, String password) {
        User user = userMapper.findBySchoolId(schoolId);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void resetUsername(Long userId, String newUsername) {
        User user = userMapper.findByUserId(userId);
        if (user != null) {
            user.setUsername(newUsername);
            user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            userMapper.updateUser(user);
        }
    }

    public void resetPassword(Long userId, String newPassword) {
        User user = userMapper.findByUserId(userId);
        if (user != null) {
            user.setPassword(newPassword);
            user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            userMapper.updateUser(user);
        }
    }

    public void resetProfileImage(Long userId, String newProfileImage) {
        User user = userMapper.findByUserId(userId);
        if (user != null) {
            user.setProfileImage(newProfileImage);
            user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            userMapper.updateUser(user);
        }
    }
}
