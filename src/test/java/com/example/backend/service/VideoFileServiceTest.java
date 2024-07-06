package com.example.backend.service;

import com.example.backend.mapper.VideoFileMapper;
import com.example.backend.model.VideoFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VideoFileServiceTest {

    @InjectMocks
    private VideoFileService videoFileService;

    @Mock
    private VideoFileMapper videoFileMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByFileId() {
        VideoFile videoFile = new VideoFile();
        videoFile.setFileId(1L);
        when(videoFileMapper.findByFileId(1L)).thenReturn(videoFile);

        VideoFile result = videoFileService.findByFileId(1L);
        assertEquals(1L, result.getFileId());
    }

    @Test
    public void testFindByVideoId() {
        List<VideoFile> videoFiles = List.of(new VideoFile(), new VideoFile());
        when(videoFileMapper.findByVideoId(1L)).thenReturn(videoFiles);

        List<VideoFile> result = videoFileService.findByVideoId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testInsertVideoFile() {
        VideoFile videoFile = new VideoFile();
        videoFileService.insertVideoFile(videoFile);
        verify(videoFileMapper, times(1)).insertVideoFile(videoFile);
    }

    @Test
    public void testDeleteVideoFile() {
        videoFileService.deleteVideoFile(1L);
        verify(videoFileMapper, times(1)).deleteVideoFile(1L);
    }
}
