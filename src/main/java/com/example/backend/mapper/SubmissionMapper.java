package com.example.backend.mapper;

import com.example.backend.model.Submission;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SubmissionMapper {
    Submission findBySubmissionId(Long submissionId);

    List<Submission> findByAssignmentId(Long assignmentId);

    List<Submission> findByStudentId(Long studentId);

    void insertSubmission(Submission submission);

    void updateSubmission(Submission submission);

    void deleteSubmission(Long submissionId);

    void deleteByUserId(Long studentId);

    List<Submission> findStudentAssignmentId(Long assignmentId, Long studentId);

    void deleteByAssignmentId(Long assignmentId);
}
