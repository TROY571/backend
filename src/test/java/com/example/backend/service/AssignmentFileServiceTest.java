package com.example.backend.service;

import com.example.backend.mapper.AssignmentFileMapper;
import com.example.backend.model.AssignmentFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AssignmentFileServiceTest {

    @InjectMocks
    private AssignmentFileService assignmentFileService;

    @Mock
    private AssignmentFileMapper assignmentFileMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByFileId() {
        AssignmentFile assignmentFile = new AssignmentFile();
        assignmentFile.setFileId(1L);
        when(assignmentFileMapper.findByFileId(1L)).thenReturn(assignmentFile);

        AssignmentFile result = assignmentFileService.findByFileId(1L);
        assertEquals(1L, result.getFileId());
    }

    @Test
    public void testFindByAssignmentId() {
        List<AssignmentFile> assignmentFiles = List.of(new AssignmentFile(), new AssignmentFile());
        when(assignmentFileMapper.findByAssignmentId(1L)).thenReturn(assignmentFiles);

        List<AssignmentFile> result = assignmentFileService.findByAssignmentId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testInsertAssignmentFile() {
        AssignmentFile assignmentFile = new AssignmentFile();
        assignmentFileService.insertAssignmentFile(assignmentFile);
        verify(assignmentFileMapper, times(1)).insertAssignmentFile(assignmentFile);
    }

    @Test
    public void testDeleteAssignmentFile() {
        assignmentFileService.deleteAssignmentFile(1L);
        verify(assignmentFileMapper, times(1)).deleteAssignmentFile(1L);
    }
}
