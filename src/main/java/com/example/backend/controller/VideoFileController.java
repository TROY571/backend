package com.example.backend.controller;

import com.example.backend.model.VideoFile;
import com.example.backend.service.VideoFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/video-files")
public class VideoFileController {

    @Autowired
    private VideoFileService videoFileService;

    @GetMapping("/{id}")
    public ResponseEntity<VideoFile> getVideoFileById(@PathVariable Long id) {
        return ResponseEntity.ok(videoFileService.findByFileId(id));
    }

    @GetMapping("/video/{videoId}")
    public ResponseEntity<List<VideoFile>> getVideoFilesByVideoId(@PathVariable Long videoId) {
        return ResponseEntity.ok(videoFileService.findByVideoId(videoId));
    }

    @PostMapping("/")
    public ResponseEntity<VideoFile> createVideoFile(@RequestBody VideoFile videoFile) {
        videoFile.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        videoFileService.insertVideoFile(videoFile);
        return ResponseEntity.ok(videoFile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoFile(@PathVariable Long id) {
        videoFileService.deleteVideoFile(id);
        return ResponseEntity.ok().build();
    }
}
