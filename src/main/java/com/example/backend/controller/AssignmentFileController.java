package com.example.backend.controller;

import com.example.backend.model.AssignmentFile;
import com.example.backend.service.AssignmentFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/assignment-files")
public class AssignmentFileController {

    @Autowired
    private AssignmentFileService assignmentFileService;

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentFile> getAssignmentFileById(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentFileService.findByFileId(id));
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<AssignmentFile>> getAssignmentFilesByAssignmentId(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(assignmentFileService.findByAssignmentId(assignmentId));
    }

    @PostMapping("/")
    public ResponseEntity<AssignmentFile> createAssignmentFile(@RequestBody AssignmentFile assignmentFile) {
        assignmentFile.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        assignmentFileService.insertAssignmentFile(assignmentFile);
        return ResponseEntity.ok(assignmentFile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignmentFile(@PathVariable Long id) {
        assignmentFileService.deleteAssignmentFile(id);
        return ResponseEntity.ok().build();
    }
}
