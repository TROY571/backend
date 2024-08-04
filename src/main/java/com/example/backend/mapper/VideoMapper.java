package com.example.backend.mapper;

import com.example.backend.model.Video;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface VideoMapper {
    Video findByVideoId(Long videoId);

    List<Video> findByCourseId(Long courseId);

    List<Video> findAllVideos();

    void insertVideo(Video video);

    void updateVideo(Video video);

    void deleteVideo(Long videoId);

    void updateAverageRating(Long videoId, Double rating);

    void deleteByCourseId(Long courseId);
}
