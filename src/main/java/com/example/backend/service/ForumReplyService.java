package com.example.backend.service;

import com.example.backend.mapper.ForumReplyMapper;
import com.example.backend.model.ForumReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ForumReplyService {

    @Autowired
    private ForumReplyMapper forumReplyMapper;

    public ForumReply findByReplyId(Long replyId) {
        return forumReplyMapper.findByReplyId(replyId);
    }

    public List<ForumReply> findByTopicId(Long topicId) {
        return forumReplyMapper.findByTopicId(topicId);
    }

    public List<ForumReply> findByUserId(Long userId) {
        return forumReplyMapper.findByUserId(userId);
    }

    public void insertForumReply(ForumReply forumReply) {
        forumReply.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        forumReplyMapper.insertForumReply(forumReply);
    }

    public void updateForumReply(ForumReply forumReply) {
        forumReplyMapper.updateForumReply(forumReply);
    }

    public void deleteForumReply(Long replyId) {
        forumReplyMapper.deleteForumReply(replyId);
    }
}
