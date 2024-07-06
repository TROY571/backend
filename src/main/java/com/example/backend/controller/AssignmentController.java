package com.example.backend.controller;

import com.example.backend.model.Assignment;
import com.example.backend.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.findByAssignmentId(id));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(assignmentService.findByCourseId(courseId));
    }

    @PostMapping("/")
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment) {
        assignment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        assignment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        assignmentService.insertAssignment(assignment);
        return ResponseEntity.ok(assignment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id, @RequestBody Assignment assignment) {
        assignment.setAssignmentId(id);
        assignment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        assignmentService.updateAssignment(assignment);
        return ResponseEntity.ok(assignment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.ok().build();
    }
}
