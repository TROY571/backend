package com.example.backend.service;

import com.example.backend.mapper.SubmissionMapper;
import com.example.backend.model.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionMapper submissionMapper;

    public Submission findBySubmissionId(Long submissionId) {
        return submissionMapper.findBySubmissionId(submissionId);
    }

    public List<Submission> findByAssignmentId(Long assignmentId) {
        return submissionMapper.findByAssignmentId(assignmentId);
    }

    public List<Submission> findByStudentId(Long studentId) {
        return submissionMapper.findByStudentId(studentId);
    }

    public void insertSubmission(Submission submission) {
        submission.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        submission.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        submissionMapper.insertSubmission(submission);
    }

    public void updateSubmission(Submission submission) {
        submission.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        submissionMapper.updateSubmission(submission);
    }

    public void deleteSubmission(Long submissionId) {
        submissionMapper.deleteSubmission(submissionId);
    }
}
