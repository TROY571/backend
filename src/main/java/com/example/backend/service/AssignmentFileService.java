package com.example.backend.service;

import com.example.backend.mapper.AssignmentFileMapper;
import com.example.backend.model.AssignmentFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AssignmentFileService {

    @Autowired
    private AssignmentFileMapper assignmentFileMapper;

    public AssignmentFile findByFileId(Long fileId) {
        return assignmentFileMapper.findByFileId(fileId);
    }

    public List<AssignmentFile> findByAssignmentId(Long assignmentId) {
        return assignmentFileMapper.findByAssignmentId(assignmentId);
    }

    public void insertAssignmentFile(AssignmentFile assignmentFile) {
        assignmentFile.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        assignmentFileMapper.insertAssignmentFile(assignmentFile);
    }

    public void deleteAssignmentFile(Long fileId) {
        assignmentFileMapper.deleteAssignmentFile(fileId);
    }
}
