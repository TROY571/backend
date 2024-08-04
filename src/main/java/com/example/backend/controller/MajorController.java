package com.example.backend.controller;

import com.example.backend.model.*;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/majors")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @GetMapping("/{id}")
    public ResponseEntity<Major> getMajorById(@PathVariable Long id) {
        return ResponseEntity.ok(majorService.findByMajorId(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Major>> getAllMajors() {
        return ResponseEntity.ok(majorService.findAllMajors());
    }

    @PostMapping("/")
    public ResponseEntity<Major> createMajor(@RequestBody Major major) {
        major.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        major.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        majorService.insertMajor(major);
        return ResponseEntity.ok(major);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Major> updateMajor(@PathVariable Long id, @RequestBody Major major) {
        major.setMajorId(id);
        major.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        majorService.updateMajor(major);

        return ResponseEntity.ok(major);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMajor(@PathVariable Long id) {
        majorService.deleteMajor(id);
        return ResponseEntity.ok().build();
    }
}
