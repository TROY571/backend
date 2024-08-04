package com.example.backend.controller;

import com.example.backend.model.VideoFile;
import com.example.backend.service.VideoFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/video-files")
public class VideoFileController {

    @Autowired
    private VideoFileService videoFileService;
    @Value("${upload.dir}")
    private String uploadDir;
    @GetMapping("/{id}")
    public ResponseEntity<VideoFile> getVideoFileById(@PathVariable Long id) {
        return ResponseEntity.ok(videoFileService.findByFileId(id));
    }

    @GetMapping("/video/{videoId}")
    public ResponseEntity<List<VideoFile>> getVideoFilesByVideoId(@PathVariable Long videoId) {
        return ResponseEntity.ok(videoFileService.findByVideoId(videoId));
    }

    @PostMapping("/")
    public ResponseEntity<String> createVideoFile(
            @RequestPart("videoFile") VideoFile videoFile,
            @RequestParam("file") MultipartFile file) {
        String result = videoFileService.insertVideoFile(videoFile, file);
        if (result.equals("Uploaded successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(400).body(result);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoFile(@PathVariable Long id) {
        videoFileService.deleteVideoFile(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/video/{videoId}")
    public ResponseEntity<Void> deleteVideoFilesByVideoId(@PathVariable Long videoId) {
        videoFileService.deleteVideoFilesByVideoId(videoId);
        return ResponseEntity.ok().build();
    }

}
