package com.example.backend.controller;

import com.example.backend.model.Comment;
import com.example.backend.model.Rating;
import com.example.backend.model.Video;
import com.example.backend.service.CommentService;
import com.example.backend.service.RatingService;
import com.example.backend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import static java.nio.file.Files.isReadable;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;
    @Value("${upload.dir}")
    private String uploadDir;
    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideoById(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.findByVideoId(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Video>> getAllVideos() {
        return ResponseEntity.ok(videoService.findAllVideos());
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Video>> getVideosByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(videoService.findByCourseId(courseId));
    }

    @PostMapping("/")
    public ResponseEntity<String> createVideo(
            @RequestPart("video") Video video,
            @RequestParam("file") MultipartFile file) {
        String result = videoService.insertVideo(video, file);
        if (result.equals("Uploaded successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(400).body(result);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVideo(
            @PathVariable Long id,
            @RequestBody Video video) {
        video.setVideoId(id);
        video.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        videoService.updateVideo(video);
        return ResponseEntity.ok("Video updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/files/{filename:.+}")
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

}
