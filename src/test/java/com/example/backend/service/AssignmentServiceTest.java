package com.example.backend.service;

import com.example.backend.mapper.AssignmentMapper;
import com.example.backend.model.Assignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AssignmentServiceTest {

    @InjectMocks
    private AssignmentService assignmentService;

    @Mock
    private AssignmentMapper assignmentMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByAssignmentId() {
        Assignment assignment = new Assignment();
        assignment.setAssignmentId(1L);
        when(assignmentMapper.findByAssignmentId(1L)).thenReturn(assignment);

        Assignment result = assignmentService.findByAssignmentId(1L);
        assertEquals(1L, result.getAssignmentId());
    }

    @Test
    public void testFindByCourseId() {
        List<Assignment> assignments = List.of(new Assignment(), new Assignment());
        when(assignmentMapper.findByCourseId(1L)).thenReturn(assignments);

        List<Assignment> result = assignmentService.findByCourseId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testInsertAssignment() {
        Assignment assignment = new Assignment();
        assignmentService.insertAssignment(assignment);
        verify(assignmentMapper, times(1)).insertAssignment(assignment);
    }

    @Test
    public void testUpdateAssignment() {
        Assignment assignment = new Assignment();
        assignmentService.updateAssignment(assignment);
        verify(assignmentMapper, times(1)).updateAssignment(assignment);
    }

    @Test
    public void testDeleteAssignment() {
        assignmentService.deleteAssignment(1L);
        verify(assignmentMapper, times(1)).deleteAssignment(1L);
    }
}
