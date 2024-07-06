package com.example.backend.mapper;

import com.example.backend.model.VideoFile;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface VideoFileMapper {
    VideoFile findByFileId(Long fileId);

    List<VideoFile> findByVideoId(Long videoId);

    void insertVideoFile(VideoFile videoFile);

    void deleteVideoFile(Long fileId);

    void deleteVideoFilesByVideoId(Long videoId);
}
