package com.example.backend.service;

import com.example.backend.mapper.SubmissionMapper;
import com.example.backend.model.Assignment;
import com.example.backend.model.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionMapper submissionMapper;
    @Value("${upload.dir}")
    private String uploadDir;
    public Submission findBySubmissionId(Long submissionId) {
        return submissionMapper.findBySubmissionId(submissionId);
    }

    public List<Submission> findByAssignmentId(Long assignmentId) {
        return submissionMapper.findByAssignmentId(assignmentId);
    }

    public List<Submission> findByStudentId(Long studentId) {
        return submissionMapper.findByStudentId(studentId);
    }
    public List<Submission> findStudentAssignmentId(Long assignmentId, Long studentId) {
        return submissionMapper.findStudentAssignmentId(assignmentId,studentId);
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

    public String insertAssignment(Submission submission, MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            if (fileName != null) {
                String filePath = uploadFile(file, submission.getStudentId());
                submission.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                submission.setSubmissionFileUrl(filePath);
                submissionMapper.insertSubmission(submission);
                return "Uploaded successfully";
            } else {
                return "Invalid file type.";
            }
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
    }
    public void deleteByUserId(Long studentId) {
        submissionMapper.deleteByUserId(studentId);
    }
    private String uploadFile(MultipartFile file, Long id) throws  IOException {
        String fileName = file.getOriginalFilename();
        String uniqueFileName = fileName.substring(0, fileName.lastIndexOf('.')) + id + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf('.'));
        Path filePath = Paths.get(uploadDir, uniqueFileName);
        Files.copy(file.getInputStream(), filePath);
        return uniqueFileName;
    }



}
