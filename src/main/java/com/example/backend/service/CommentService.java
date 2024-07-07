package com.example.backend.service;

import com.example.backend.mapper.CommentMapper;
import com.example.backend.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public Comment findByCommentId(Long commentId) {
        return commentMapper.findByCommentId(commentId);
    }

    public List<Comment> findByVideoId(Long videoId) {
        return commentMapper.findByVideoId(videoId);
    }

    public List<Comment> findByUserId(Long userId) {
        return commentMapper.findByUserId(userId);
    }

    public void insertComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    public void updateComment(Comment comment) {
        commentMapper.updateComment(comment);
    }

    public void deleteComment(Long commentId) {
        deleteNestedComments(commentId);
        commentMapper.deleteComment(commentId);
    }

    private void deleteNestedComments(Long parentCommentId) {
        List<Comment> nestedComments = commentMapper.findByParentCommentId(parentCommentId);
        for (Comment nestedComment : nestedComments) {
            deleteNestedComments(nestedComment.getCommentId());
            commentMapper.deleteComment(nestedComment.getCommentId());
        }
    }
}
