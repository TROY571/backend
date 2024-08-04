package com.example.backend.mapper;

import com.example.backend.model.Rating;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RatingMapper {
    Rating findByRatingId(Long ratingId);

    List<Rating> findByVideoId(Long videoId);

    void insertRating(Rating rating);

    void updateRating(Rating rating);

    void deleteRating(Long ratingId);

    Double findAverageRatingByVideoId(Long videoId);

    void deleteByUserId(Long userId);

    void deleteByVideoId(Long videoId);
}
