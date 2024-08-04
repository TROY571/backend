package com.example.backend.service;

import com.example.backend.mapper.AssignmentMapper;
import com.example.backend.mapper.SubmissionMapper;
import com.example.backend.model.Assignment;
import lombok.val;
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
public class AssignmentService {

    @Value("${upload.dir}")
    private String uploadDir;
    @Autowired
    private AssignmentMapper assignmentMapper;
    @Autowired
    private SubmissionMapper submissionMapper;

    public Assignment findByAssignmentId(Long assignmentId) {
        return assignmentMapper.findByAssignmentId(assignmentId);
    }

    public List<Assignment> findByCourseId(Long courseId) {
        return assignmentMapper.findByCourseId(courseId);
    }

    public String insertAssignment(Assignment assignment, MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            if (fileName != null) {
                String filePath = uploadFile(file, assignment.getCourseId());
                assignment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                assignment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                assignment.setAssignmentFileName(fileName);
                assignment.setAssignmentFileUrl(filePath);
                assignmentMapper.insertAssignment(assignment);
                return "Uploaded successfully";
            } else {
                return "Invalid file type.";
            }
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
    }
    private String uploadFile(MultipartFile file, Long id) throws  IOException {
        String fileName = file.getOriginalFilename();
        String uniqueFileName = fileName.substring(0, fileName.lastIndexOf('.')) + id + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf('.'));
        Path filePath = Paths.get(uploadDir, uniqueFileName);
        Files.copy(file.getInputStream(), filePath);
        return uniqueFileName;
    }

    public void updateAssignment(Assignment assignment) {
        assignment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        assignmentMapper.updateAssignment(assignment);
    }

    public void deleteAssignment(Long assignmentId) {
        submissionMapper.deleteByAssignmentId(assignmentId);
        assignmentMapper.deleteAssignment(assignmentId);
    }

    public void deleteByUserId(Long createdBy) {
        val byUserId = assignmentMapper.findByUserId(createdBy);
        for (Assignment assignment : byUserId) {
            deleteAssignment(assignment.getAssignmentId());
        }
    }

    public void deleteByCourseId(Long courseId) {
        val byCourseId = assignmentMapper.findByCourseId(courseId);
        for (Assignment assignment : byCourseId) {
            deleteAssignment(assignment.getAssignmentId());
        }
    }
}
