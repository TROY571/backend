package com.example.backend.service;

import com.example.backend.mapper.MajorMapper;
import com.example.backend.model.Major;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MajorServiceTest {

    @InjectMocks
    private MajorService majorService;

    @Mock
    private MajorMapper majorMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByMajorId() {
        Major major = new Major();
        major.setMajorId(1L);
        when(majorMapper.findByMajorId(1L)).thenReturn(major);

        Major result = majorService.findByMajorId(1L);
        assertEquals(1L, result.getMajorId());
    }

    @Test
    public void testFindAllMajors() {
        List<Major> majors = List.of(new Major(), new Major());
        when(majorMapper.findAllMajors()).thenReturn(majors);

        List<Major> result = majorService.findAllMajors();
        assertEquals(2, result.size());
    }

    @Test
    public void testInsertMajor() {
        Major major = new Major();
        majorService.insertMajor(major);
        verify(majorMapper, times(1)).insertMajor(major);
    }

    @Test
    public void testUpdateMajor() {
        Major major = new Major();
        majorService.updateMajor(major);
        verify(majorMapper, times(1)).updateMajor(major);
    }

    @Test
    public void testDeleteMajor() {
        majorService.deleteMajor(1L);
        verify(majorMapper, times(1)).deleteMajor(1L);
    }
}
