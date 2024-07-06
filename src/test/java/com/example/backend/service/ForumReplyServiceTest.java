package com.example.backend.service;

import com.example.backend.mapper.ForumReplyMapper;
import com.example.backend.model.ForumReply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ForumReplyServiceTest {

    @InjectMocks
    private ForumReplyService forumReplyService;

    @Mock
    private ForumReplyMapper forumReplyMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByReplyId() {
        ForumReply forumReply = new ForumReply();
        forumReply.setReplyId(1L);
        when(forumReplyMapper.findByReplyId(1L)).thenReturn(forumReply);

        ForumReply result = forumReplyService.findByReplyId(1L);
        assertEquals(1L, result.getReplyId());
    }

    @Test
    public void testFindByTopicId() {
        List<ForumReply> forumReplies = List.of(new ForumReply(), new ForumReply());
        when(forumReplyMapper.findByTopicId(1L)).thenReturn(forumReplies);

        List<ForumReply> result = forumReplyService.findByTopicId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindByUserId() {
        List<ForumReply> forumReplies = List.of(new ForumReply(), new ForumReply());
        when(forumReplyMapper.findByUserId(1L)).thenReturn(forumReplies);

        List<ForumReply> result = forumReplyService.findByUserId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testInsertForumReply() {
        ForumReply forumReply = new ForumReply();
        forumReplyService.insertForumReply(forumReply);
        verify(forumReplyMapper, times(1)).insertForumReply(forumReply);
    }

    @Test
    public void testUpdateForumReply() {
        ForumReply forumReply = new ForumReply();
        forumReplyService.updateForumReply(forumReply);
        verify(forumReplyMapper, times(1)).updateForumReply(forumReply);
    }

    @Test
    public void testDeleteForumReply() {
        forumReplyService.deleteForumReply(1L);
        verify(forumReplyMapper, times(1)).deleteForumReply(1L);
    }
}
