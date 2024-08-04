package com.example.backend.service;

import com.example.backend.mapper.CommentMapper;
import com.example.backend.model.Comment;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
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

    public void deleteByUserId(Long userId) {
        List<Comment> comments = commentMapper.findByUserId(userId);
        handle(comments);
    }

    public void deleteByVideoId(Long videoId) {
        handle(findByVideoId(videoId));

    }

    private void handle(List<Comment> comments){
        for (Comment comment : comments) {
            List<Comment> byParentCommentId = commentMapper.findByParentCommentId(comment.getCommentId());
            for (Comment comment1 : byParentCommentId) {
                commentMapper.deleteComment(comment1.getCommentId());
            }
            commentMapper.deleteComment(comment.getCommentId());
        }
    }
}
