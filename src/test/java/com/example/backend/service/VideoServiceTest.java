package com.example.backend.service;

import com.example.backend.mapper.VideoMapper;
import com.example.backend.model.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VideoServiceTest {

    @InjectMocks
    private VideoService videoService;

    @Mock
    private VideoMapper videoMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByVideoId() {
        Video video = new Video();
        video.setVideoId(1L);
        when(videoMapper.findByVideoId(1L)).thenReturn(video);

        Video result = videoService.findByVideoId(1L);
        assertEquals(1L, result.getVideoId());
    }

    @Test
    public void testFindByCourseId() {
        List<Video> videos = List.of(new Video(), new Video());
        when(videoMapper.findByCourseId(1L)).thenReturn(videos);

        List<Video> result = videoService.findByCourseId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindAllVideos() {
        List<Video> videos = List.of(new Video(), new Video());
        when(videoMapper.findAllVideos()).thenReturn(videos);

        List<Video> result = videoService.findAllVideos();
        assertEquals(2, result.size());
    }

    @Test
    public void testInsertVideo() {
        Video video = new Video();
        videoService.insertVideo(video);
        verify(videoMapper, times(1)).insertVideo(video);
    }

    @Test
    public void testUpdateVideo() {
        Video video = new Video();
        videoService.updateVideo(video);
        verify(videoMapper, times(1)).updateVideo(video);
    }

    @Test
    public void testDeleteVideo() {
        videoService.deleteVideo(1L);
        verify(videoMapper, times(1)).deleteVideo(1L);
    }
}
