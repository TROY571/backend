package com.example.backend.service;

import com.example.backend.mapper.*;
import com.example.backend.model.Video;
import lombok.val;
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
public class VideoService {

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private VideoFileMapper videoFileMapper;
    @Autowired
    private CommentService commentServer;
    @Autowired
    private RatingMapper ratingMapper;
    @Autowired
    private FavoriteMapper favoriteMapper;


    @Value("${upload.dir}")
    private String uploadDir;
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
        return uniqueFileName;
    }

    public void updateVideo(Video video) {
        video.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        videoMapper.updateVideo(video);
    }
    public void deleteVideo(Long videoId) {
        commentServer.deleteByVideoId(videoId);
        ratingMapper.deleteByVideoId(videoId);
        favoriteMapper.deleteByVideoId(videoId);
        videoFileMapper.deleteVideoFilesByVideoId(videoId);
        videoMapper.deleteVideo(videoId);
    }

    public void deleteByCourseId(Long courseId) {
        List<Video> byCourseId = videoMapper.findByCourseId(courseId);
        for (Video video : byCourseId) {
            deleteVideo(video.getVideoId());
        }
    }
}
