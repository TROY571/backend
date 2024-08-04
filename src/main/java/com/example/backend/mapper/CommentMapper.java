package com.example.backend.mapper;

import com.example.backend.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CommentMapper {
    Comment findByCommentId(Long commentId);

    List<Comment> findByVideoId(Long videoId);

    List<Comment> findByUserId(Long userId);

    void insertComment(Comment comment);

    void updateComment(Comment comment);

    void deleteComment(Long commentId);

    List<Comment> findByParentCommentId(Long parentCommentId);

    void deleteByUserId(Long userId);

    void deleteByVideoId(Long videoId);
}
