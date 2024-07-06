package com.example.backend.controller;

import com.example.backend.model.Submission;
import com.example.backend.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable Long id) {
        return ResponseEntity.ok(submissionService.findBySubmissionId(id));
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<Submission>> getSubmissionsByAssignmentId(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(submissionService.findByAssignmentId(assignmentId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Submission>> getSubmissionsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(submissionService.findByStudentId(studentId));
    }

    @PostMapping("/")
    public ResponseEntity<Submission> createSubmission(@RequestBody Submission submission) {
        submission.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        submission.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        submissionService.insertSubmission(submission);
        return ResponseEntity.ok(submission);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Submission> updateSubmission(@PathVariable Long id, @RequestBody Submission submission) {
        submission.setSubmissionId(id);
        submission.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        submissionService.updateSubmission(submission);
        return ResponseEntity.ok(submission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long id) {
        submissionService.deleteSubmission(id);
        return ResponseEntity.ok().build();
    }
}
