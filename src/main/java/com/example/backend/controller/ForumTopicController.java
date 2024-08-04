package com.example.backend.controller;

import com.example.backend.model.ForumTopic;
import com.example.backend.model.ForumTopicDto;
import com.example.backend.service.ForumTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/forum-topics")
public class ForumTopicController {

    @Autowired
    private ForumTopicService forumTopicService;

    @GetMapping("/{id}")
    public ResponseEntity<ForumTopic> getForumTopicById(@PathVariable Long id) {
        return ResponseEntity.ok(forumTopicService.findByTopicId(id));
    }

    @GetMapping("/major/{majorId}")
    public ResponseEntity<List<ForumTopicDto>> getForumTopicsByMajorId(@PathVariable Long majorId) {
        return ResponseEntity.ok(forumTopicService.findByMajorId(majorId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ForumTopic>> getForumTopicsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(forumTopicService.findByUserId(userId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ForumTopic>> getForumTopicsByTitle(@RequestParam String title) {
        return ResponseEntity.ok(forumTopicService.findByTopicTitle(title));
    }

    @PostMapping("/")
    public ResponseEntity<ForumTopic> createForumTopic(@RequestBody ForumTopic forumTopic) {
        forumTopic.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        forumTopicService.insertForumTopic(forumTopic);
        return ResponseEntity.ok(forumTopic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ForumTopic> updateForumTopic(@PathVariable Long id, @RequestBody ForumTopic forumTopic) {
        forumTopic.setTopicId(id);
        forumTopicService.updateForumTopic(forumTopic);
        return ResponseEntity.ok(forumTopic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForumTopic(@PathVariable Long id) {
        forumTopicService.deleteForumTopic(id);
        return ResponseEntity.ok().build();
    }
}
