package com.example.backend.mapper;

import com.example.backend.model.ForumReply;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ForumReplyMapper {
    ForumReply findByReplyId(Long replyId);

    List<ForumReply> findByTopicId(Long topicId);

    List<ForumReply> findByUserId(Long userId);

    void insertForumReply(ForumReply forumReply);

    void updateForumReply(ForumReply forumReply);

    void deleteForumReply(Long replyId);

    void deleteByUserId(Long userId);
}
