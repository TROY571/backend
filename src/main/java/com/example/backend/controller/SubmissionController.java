package com.example.backend.controller;

import com.example.backend.model.Assignment;
import com.example.backend.model.Submission;
import com.example.backend.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @GetMapping("/student/{assignmentId}/{studentId}")
    public ResponseEntity<List<Submission>> getStudentSubmissions(@PathVariable Long assignmentId,@PathVariable Long studentId) {
        return ResponseEntity.ok(submissionService.findStudentAssignmentId(assignmentId,studentId));
    }

    @PostMapping("/")
    public ResponseEntity<?> createSubmission(@RequestBody Submission submission) {
        submission.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        submission.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        submissionService.insertSubmission(submission);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/upload")
    public ResponseEntity<String> createSubmission(
            @RequestPart("submission") Submission submission,
            @RequestParam("file") MultipartFile file) {
        String result = submissionService.insertAssignment(submission, file);
        if (result.equals("Uploaded successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(400).body(result);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Submission> updateSubmission(@PathVariable Long id,@RequestBody Submission submission) {
        Submission bySubmissionId = submissionService.findBySubmissionId(id);
        bySubmissionId.setFeedback(submission.getFeedback());
        bySubmissionId.setGrade(submission.getGrade());
        bySubmissionId.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        submissionService.updateSubmission(bySubmissionId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long id) {
        submissionService.deleteSubmission(id);
        return ResponseEntity.ok().build();
    }
}
