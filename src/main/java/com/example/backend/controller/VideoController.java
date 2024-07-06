package com.example.backend.controller;

import com.example.backend.model.Video;
import com.example.backend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

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
    public ResponseEntity<Video> createVideo(@RequestBody Video video) {
        video.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        video.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        videoService.insertVideo(video);
        return ResponseEntity.ok(video);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Video> updateVideo(@PathVariable Long id, @RequestBody Video video) {
        video.setVideoId(id);
        video.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        videoService.updateVideo(video);
        return ResponseEntity.ok(video);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return ResponseEntity.ok().build();
    }
}
