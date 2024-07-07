package com.example.backend.service;

import com.example.backend.mapper.ForumReplyMapper;
import com.example.backend.mapper.ForumTopicMapper;
import com.example.backend.model.ForumReply;
import com.example.backend.model.ForumTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ForumTopicService {
    @Autowired
    private ForumTopicMapper forumTopicMapper;

    @Autowired
    private ForumReplyMapper forumReplyMapper;

    public ForumTopic findByTopicId(Long topicId) {
        return forumTopicMapper.findByTopicId(topicId);
    }

    public List<ForumTopic> findByMajorId(Long majorId) {
        return forumTopicMapper.findByMajorId(majorId);
    }

    public List<ForumTopic> findByUserId(Long userId) {
        return forumTopicMapper.findByUserId(userId);
    }

    public List<ForumTopic> findByTopicTitle(String topicTitle) {
        return forumTopicMapper.findByTopicTitle(topicTitle);
    }

    public void insertForumTopic(ForumTopic forumTopic) {
        forumTopicMapper.insertForumTopic(forumTopic);
    }

    public void updateForumTopic(ForumTopic forumTopic) {
        forumTopicMapper.updateForumTopic(forumTopic);
    }

    public void deleteForumTopic(Long topicId) {
        // Delete related forum replies first
        List<ForumReply> replies = forumReplyMapper.findByTopicId(topicId);
        for (ForumReply reply : replies) {
            forumReplyMapper.deleteForumReply(reply.getReplyId());
        }
        // Delete the forum topic
        forumTopicMapper.deleteForumTopic(topicId);
    }
}
