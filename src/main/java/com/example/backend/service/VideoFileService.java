package com.example.backend.service;

import com.example.backend.mapper.VideoFileMapper;
import com.example.backend.model.VideoFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class VideoFileService {

    @Autowired
    private VideoFileMapper videoFileMapper;

    public VideoFile findByFileId(Long fileId) {
        return videoFileMapper.findByFileId(fileId);
    }

    public List<VideoFile> findByVideoId(Long videoId) {
        return videoFileMapper.findByVideoId(videoId);
    }

    public void insertVideoFile(VideoFile videoFile) {
        videoFile.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        videoFileMapper.insertVideoFile(videoFile);
    }

    public void deleteVideoFile(Long fileId) {
        videoFileMapper.deleteVideoFile(fileId);
    }
}
