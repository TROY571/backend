package com.example.backend.service;

import com.example.backend.mapper.AssignmentMapper;
import com.example.backend.model.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentMapper assignmentMapper;

    public Assignment findByAssignmentId(Long assignmentId) {
        return assignmentMapper.findByAssignmentId(assignmentId);
    }

    public List<Assignment> findByCourseId(Long courseId) {
        return assignmentMapper.findByCourseId(courseId);
    }

    public void insertAssignment(Assignment assignment) {
        assignment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        assignment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        assignmentMapper.insertAssignment(assignment);
    }

    public void updateAssignment(Assignment assignment) {
        assignment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        assignmentMapper.updateAssignment(assignment);
    }

    public void deleteAssignment(Long assignmentId) {
        assignmentMapper.deleteAssignment(assignmentId);
    }
}
