package com.example.backend.controller;

import com.example.backend.model.ForumReply;
import com.example.backend.service.ForumReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/forum-replies")
public class ForumReplyController {

    @Autowired
    private ForumReplyService forumReplyService;

    @GetMapping("/{id}")
    public ResponseEntity<ForumReply> getForumReplyById(@PathVariable Long id) {
        return ResponseEntity.ok(forumReplyService.findByReplyId(id));
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<ForumReply>> getForumRepliesByTopicId(@PathVariable Long topicId) {
        return ResponseEntity.ok(forumReplyService.findByTopicId(topicId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ForumReply>> getForumRepliesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(forumReplyService.findByUserId(userId));
    }

    @PostMapping("/")
    public ResponseEntity<ForumReply> createForumReply(@RequestBody ForumReply forumReply) {
        forumReply.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        forumReplyService.insertForumReply(forumReply);
        return ResponseEntity.ok(forumReply);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ForumReply> updateForumReply(@PathVariable Long id, @RequestBody ForumReply forumReply) {
        forumReply.setReplyId(id);
        forumReplyService.updateForumReply(forumReply);
        return ResponseEntity.ok(forumReply);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForumReply(@PathVariable Long id) {
        forumReplyService.deleteForumReply(id);
        return ResponseEntity.ok().build();
    }
}
