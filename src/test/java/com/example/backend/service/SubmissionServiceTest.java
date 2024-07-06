package com.example.backend.service;

import com.example.backend.mapper.SubmissionMapper;
import com.example.backend.model.Submission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SubmissionServiceTest {

    @InjectMocks
    private SubmissionService submissionService;

    @Mock
    private SubmissionMapper submissionMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindBySubmissionId() {
        Submission submission = new Submission();
        submission.setSubmissionId(1L);
        when(submissionMapper.findBySubmissionId(1L)).thenReturn(submission);

        Submission result = submissionService.findBySubmissionId(1L);
        assertEquals(1L, result.getSubmissionId());
    }

    @Test
    public void testFindByAssignmentId() {
        List<Submission> submissions = List.of(new Submission(), new Submission());
        when(submissionMapper.findByAssignmentId(1L)).thenReturn(submissions);

        List<Submission> result = submissionService.findByAssignmentId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindByStudentId() {
        List<Submission> submissions = List.of(new Submission(), new Submission());
        when(submissionMapper.findByStudentId(1L)).thenReturn(submissions);

        List<Submission> result = submissionService.findByStudentId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testInsertSubmission() {
        Submission submission = new Submission();
        submissionService.insertSubmission(submission);
        verify(submissionMapper, times(1)).insertSubmission(submission);
    }

    @Test
    public void testUpdateSubmission() {
        Submission submission = new Submission();
        submissionService.updateSubmission(submission);
        verify(submissionMapper, times(1)).updateSubmission(submission);
    }

    @Test
    public void testDeleteSubmission() {
        submissionService.deleteSubmission(1L);
        verify(submissionMapper, times(1)).deleteSubmission(1L);
    }
}
