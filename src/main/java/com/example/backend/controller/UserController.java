package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.service.*;
import com.example.backend.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Value("${upload.dir}")
    private String uploadDir;
    @Autowired
    private UserService userService;
    @Autowired
    private ForumReplyService forumReplyService;
    @Autowired
    private ForumTopicService forumTopicService;
    @Autowired
    private UserCourseService userCourseService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private SubmissionService submissionService;
    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private CourseTeacherService courseTeacherService;
    @Autowired
    private RatingService ratingService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        try {
            userService.saveUser(user);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            String errorMessage = "An error occurred while registering the user.";
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                errorMessage = "User registration failed. Please check your input or try again later.";
            }
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok().build();
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        Map<String, String> errorResponse = new HashMap<>();
        try {
            Long schoolId = Long.parseLong(loginRequest.get("schoolId"));
            String password = loginRequest.get("password");

            User user = userService.login(schoolId, password);
            if (user != null) {
                Map<String, String> payload = new HashMap<>();


                payload.put("username", user.getUsername());
                String token = JWTUtil.createToken(payload);
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("token", token);
                responseBody.put("role", user.getRole().toString());
                responseBody.put("userId", user.getUserId());
                responseBody.put("majorId", user.getMajorId());
                return ResponseEntity.ok(responseBody);
            } else {

                errorResponse.put("error", "Username or password incorrect");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (NumberFormatException e) {
            errorResponse.put("error", "Invalid school ID format");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            errorResponse.put("error", "An unexpected error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
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

    @PostMapping("/{userId}/reset-profile-image")
    public ResponseEntity<?> resetProfileImage(@PathVariable Long userId, @RequestParam("newProfileImage") MultipartFile file) {
        try {
            userService.resetProfileImage(userId, file);
        return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating profile image: " + e.getMessage());
        }
    }
    @GetMapping("/img/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path file = Paths.get(uploadDir, filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok().body(resource);
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
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
    @GetMapping("/userinfo/{userId}")
    public ResponseEntity<?> getUserInfoByUserId(@PathVariable Long userId) {
        User user = userService.findByUserId(userId);
        if (user != null) {
            user.setPassword("");
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setUserId(id);
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        try {
            userService.updateUser(user);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            String errorMessage = "An error occurred while registering the user.";
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                errorMessage = "User registration failed. Please check your input or try again later.";
            }
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        forumReplyService.deleteByUserId(id);
        forumTopicService.deleteByUserId(id);
        userCourseService.deleteByUserId(id);
        commentService.deleteByUserId(id);
        favoriteService.deleteByUserId(id);
        submissionService.deleteByUserId(id);
        assignmentService.deleteByUserId(id);
        courseTeacherService.deleteByUserId(id);
        ratingService.deleteByUserId(id);
        userService.deleteUser(id);

        return ResponseEntity.ok().build();
    }
}
