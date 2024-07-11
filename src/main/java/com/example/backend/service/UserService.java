package com.example.backend.service;

import com.example.backend.mapper.UserMapper;
import com.example.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void saveUser(User user) {
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
        userMapper.updateUser(user);
    }

    public void deleteUser(Long userId) {
        userMapper.deleteUser(userId);
    }

    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    public List<User> findByRoleAndMajor(String role, Long majorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("role", role);
        params.put("majorId", majorId);
        return userMapper.findByRoleAndMajor(params);
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
            userMapper.updateUser(user);
        }
    }

    public void resetPassword(Long userId, String newPassword) {
        User user = userMapper.findByUserId(userId);
        if (user != null) {
            user.setPassword(newPassword);
            userMapper.updateUser(user);
        }
    }

    public void resetProfileImage(Long userId, String newProfileImage) {
        User user = userMapper.findByUserId(userId);
        if (user != null) {
            user.setProfileImage(newProfileImage);
            userMapper.updateUser(user);
        }
    }

}
