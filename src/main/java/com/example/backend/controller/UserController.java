package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.service.UserService;
import com.example.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        try {
            Long schoolId = Long.parseLong(loginRequest.get("schoolId"));
            String password = loginRequest.get("password");

            User user = userService.login(schoolId, password);
            if (user != null) {
                String token = jwtUtil.generateToken(user.getUsername());
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("token", token);
                responseBody.put("role", user.getRole().toString());
                responseBody.put("userId", user.getUserId());
                responseBody.put("majorId", user.getMajorId()); // Include majorId in response
                return ResponseEntity.ok(responseBody);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid school ID format");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
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
        User user = userService.findByUserId(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
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

    @GetMapping("/role-and-major")
    public ResponseEntity<List<User>> getUsersByRoleAndMajor(@RequestParam String role, @RequestParam Long majorId) {
        List<User> users = userService.findByRoleAndMajor(role, majorId);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/username/{userId}")
    public ResponseEntity<String> getUsernameByUserId(@PathVariable Long userId) {
        User user = userService.findByUserId(userId);
        if (user != null) {
            return ResponseEntity.ok(user.getUsername());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
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
