package com.example.backend.service;

import com.example.backend.mapper.VideoFileMapper;
import com.example.backend.model.VideoFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

@Service
public class VideoFileService {

    @Autowired
    private VideoFileMapper videoFileMapper;

    @Value("${upload.dir}")
    private String uploadDir;

    public VideoFile findByFileId(Long fileId) {
        return videoFileMapper.findByFileId(fileId);
    }

    public List<VideoFile> findByVideoId(Long videoId) {
        return videoFileMapper.findByVideoId(videoId);
    }

    public String insertVideoFile(VideoFile videoFile, MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            if (fileName != null) {
                String filePath = uploadFile(file, videoFile.getVideoId());
                videoFile.setFileUrl(filePath);
                videoFile.setFileName(fileName);
                videoFile.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                videoFileMapper.insertVideoFile(videoFile);
                return "Uploaded successfully";
            } else {
                return "Invalid file type.";
            }
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
    }

    private String uploadFile(MultipartFile file, Long videoId) throws IOException {
        String fileName = file.getOriginalFilename();
        String uniqueFileName = fileName.substring(0, fileName.lastIndexOf('.')) + videoId + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf('.'));
        Path filePath = Paths.get(uploadDir, uniqueFileName);
        Files.copy(file.getInputStream(), filePath);
//        return filePath.toString().replace("\\", "/");
        return uniqueFileName;
    }

    public void deleteVideoFile(Long fileId) {
        VideoFile videoFile = videoFileMapper.findByFileId(fileId);
        if (videoFile != null) {
            Path filePath = Paths.get(videoFile.getFileUrl());
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            videoFileMapper.deleteVideoFile(fileId);
        }
    }

    public void deleteVideoFilesByVideoId(Long videoId) {
        List<VideoFile> videoFiles = videoFileMapper.findByVideoId(videoId);
        for (VideoFile videoFile : videoFiles) {
            deleteVideoFile(videoFile.getFileId());
        }
    }
}
