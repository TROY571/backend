package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam Long schoolId, @RequestParam String password) {
        User user = userService.login(schoolId, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(401).build(); // Unauthorized
    }

    @PostMapping("/{id}/reset-username")
    public ResponseEntity<Void> resetUsername(@PathVariable Long id, @RequestParam String newUsername) {
        userService.resetUsername(id, newUsername);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/reset-password")
    public ResponseEntity<Void> resetPassword(@PathVariable Long id, @RequestParam String newPassword) {
        userService.resetPassword(id, newPassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/reset-profile-image")
    public ResponseEntity<Void> resetProfileImage(@PathVariable Long id, @RequestParam String newProfileImage) {
        userService.resetProfileImage(id, newProfileImage);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{schoolId}")
    public ResponseEntity<User> getUserBySchoolId(@PathVariable Long schoolId) {
        return ResponseEntity.ok(userService.findBySchoolId(schoolId));
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findByUserId(userId));
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/major/{majorId}")
    public ResponseEntity<List<User>> getUsersByMajorId(@PathVariable Long majorId) {
        return ResponseEntity.ok(userService.findByMajorId(majorId));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable User.Role role) {
        return ResponseEntity.ok(userService.findByRole(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setUserId(id);
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userService.updateUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
