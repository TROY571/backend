package com.example.backend.service;

import com.example.backend.mapper.CommentMapper;
import com.example.backend.model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentMapper commentMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByCommentId() {
        Comment comment = new Comment();
        comment.setCommentId(1L);
        when(commentMapper.findByCommentId(1L)).thenReturn(comment);

        Comment result = commentService.findByCommentId(1L);
        assertEquals(1L, result.getCommentId());
    }

    @Test
    public void testFindByVideoId() {
        List<Comment> comments = List.of(new Comment(), new Comment());
        when(commentMapper.findByVideoId(1L)).thenReturn(comments);

        List<Comment> result = commentService.findByVideoId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindByUserId() {
        List<Comment> comments = List.of(new Comment(), new Comment());
        when(commentMapper.findByUserId(1L)).thenReturn(comments);

        List<Comment> result = commentService.findByUserId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testInsertComment() {
        Comment comment = new Comment();
        commentService.insertComment(comment);
        verify(commentMapper, times(1)).insertComment(comment);
    }

    @Test
    public void testUpdateComment() {
        Comment comment = new Comment();
        commentService.updateComment(comment);
        verify(commentMapper, times(1)).updateComment(comment);
    }

    @Test
    public void testDeleteComment() {
        commentService.deleteComment(1L);
        verify(commentMapper, times(1)).deleteComment(1L);
    }
}
