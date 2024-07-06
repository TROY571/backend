package com.example.backend.service;

import com.example.backend.mapper.ForumTopicMapper;
import com.example.backend.model.ForumTopic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ForumTopicServiceTest {

    @InjectMocks
    private ForumTopicService forumTopicService;

    @Mock
    private ForumTopicMapper forumTopicMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByTopicId() {
        ForumTopic forumTopic = new ForumTopic();
        forumTopic.setTopicId(1L);
        when(forumTopicMapper.findByTopicId(1L)).thenReturn(forumTopic);

        ForumTopic result = forumTopicService.findByTopicId(1L);
        assertEquals(1L, result.getTopicId());
    }

    @Test
    public void testFindByMajorId() {
        List<ForumTopic> forumTopics = List.of(new ForumTopic(), new ForumTopic());
        when(forumTopicMapper.findByMajorId(1L)).thenReturn(forumTopics);

        List<ForumTopic> result = forumTopicService.findByMajorId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindByUserId() {
        List<ForumTopic> forumTopics = List.of(new ForumTopic(), new ForumTopic());
        when(forumTopicMapper.findByUserId(1L)).thenReturn(forumTopics);

        List<ForumTopic> result = forumTopicService.findByUserId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindByTopicTitle() {
        List<ForumTopic> forumTopics = List.of(new ForumTopic(), new ForumTopic());
        when(forumTopicMapper.findByTopicTitle("testTitle")).thenReturn(forumTopics);

        List<ForumTopic> result = forumTopicService.findByTopicTitle("testTitle");
        assertEquals(2, result.size());
    }

    @Test
    public void testInsertForumTopic() {
        ForumTopic forumTopic = new ForumTopic();
        forumTopicService.insertForumTopic(forumTopic);
        verify(forumTopicMapper, times(1)).insertForumTopic(forumTopic);
    }

    @Test
    public void testUpdateForumTopic() {
        ForumTopic forumTopic = new ForumTopic();
        forumTopicService.updateForumTopic(forumTopic);
        verify(forumTopicMapper, times(1)).updateForumTopic(forumTopic);
    }

    @Test
    public void testDeleteForumTopic() {
        forumTopicService.deleteForumTopic(1L);
        verify(forumTopicMapper, times(1)).deleteForumTopic(1L);
    }
}
