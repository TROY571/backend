package com.example.backend.service;

import com.example.backend.mapper.VideoMapper;
import com.example.backend.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoMapper videoMapper;

    private final String uploadDir = "D:/BPR/backend/uploads";

    public Video findByVideoId(Long videoId) {
        return videoMapper.findByVideoId(videoId);
    }

    public List<Video> findByCourseId(Long courseId) {
        return videoMapper.findByCourseId(courseId);
    }

    public List<Video> findAllVideos() {
        return videoMapper.findAllVideos();
    }

    public String insertVideo(Video video, MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            if (fileName != null && (fileName.endsWith(".mp4") || fileName.endsWith(".avi"))) {
                String filePath = uploadFile(file, video.getUploadedBy());
                video.setUrl(filePath);
                video.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                video.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                videoMapper.insertVideo(video);
                return "Uploaded successfully";
            } else {
                return "Invalid file type. Only .mp4 and .avi files are allowed.";
            }
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
    }

    private String uploadFile(MultipartFile file, Long userId) throws IOException {
        String fileName = file.getOriginalFilename();
        String uniqueFileName = fileName.substring(0, fileName.length() - 4) + userId + System.currentTimeMillis() + ".mp4";
        Path filePath = Paths.get(uploadDir, uniqueFileName);
        Files.copy(file.getInputStream(), filePath);
        return filePath.toString();
    }

    public void updateVideo(Video video) {
        video.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        videoMapper.updateVideo(video);
    }

    public void deleteVideo(Long videoId) {
        videoMapper.deleteVideo(videoId);
    }
}
