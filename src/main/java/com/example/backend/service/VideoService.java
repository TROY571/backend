package com.example.backend.service;

import com.example.backend.mapper.VideoMapper;
import com.example.backend.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoMapper videoMapper;

    public Video findByVideoId(Long videoId) {
        return videoMapper.findByVideoId(videoId);
    }

    public List<Video> findByCourseId(Long courseId) {
        return videoMapper.findByCourseId(courseId);
    }

    public List<Video> findAllVideos() {
        return videoMapper.findAllVideos();
    }

    public void insertVideo(Video video) {
        video.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        video.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        videoMapper.insertVideo(video);
    }

    public void updateVideo(Video video) {
        video.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        videoMapper.updateVideo(video);
    }

    public void deleteVideo(Long videoId) {
        videoMapper.deleteVideo(videoId);
    }
}
