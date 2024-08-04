package com.example.backend.mapper;

import com.example.backend.model.ForumTopic;
import com.example.backend.model.ForumTopicDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ForumTopicMapper {
    ForumTopic findByTopicId(Long topicId);

    List<ForumTopicDto> findByMajorId(Long majorId);

    List<ForumTopic> findByUserId(Long userId);

    List<ForumTopic> findByTopicTitle(String topicTitle);

    void insertForumTopic(ForumTopic forumTopic);

    void updateForumTopic(ForumTopic forumTopic);

    void deleteForumTopic(Long topicId);

    void deleteByUserId(Long userId);
}
