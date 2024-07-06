package com.example.backend.service;

import com.example.backend.mapper.RatingMapper;
import com.example.backend.mapper.VideoMapper;
import com.example.backend.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingMapper ratingMapper;

    @Autowired
    private VideoMapper videoMapper;

    public Rating findByRatingId(Long ratingId) {
        return ratingMapper.findByRatingId(ratingId);
    }

    public List<Rating> findByVideoId(Long videoId) {
        return ratingMapper.findByVideoId(videoId);
    }

    public void insertRating(Rating rating) {
        rating.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        ratingMapper.insertRating(rating);
        updateVideoAverageRating(rating.getVideoId());
    }

    public void updateRating(Rating rating) {
        ratingMapper.updateRating(rating);
        updateVideoAverageRating(rating.getVideoId());
    }

    public void deleteRating(Long ratingId) {
        Rating rating = ratingMapper.findByRatingId(ratingId);
        if (rating != null) {
            ratingMapper.deleteRating(ratingId);
            updateVideoAverageRating(rating.getVideoId());
        }
    }

    public Double findAverageRatingByVideoId(Long videoId) {
        Double averageRating = ratingMapper.findAverageRatingByVideoId(videoId);
        updateVideoAverageRating(videoId, averageRating);
        return averageRating;
    }

    private void updateVideoAverageRating(Long videoId) {
        Double averageRating = ratingMapper.findAverageRatingByVideoId(videoId);
        videoMapper.updateAverageRating(videoId, averageRating);
    }

    private void updateVideoAverageRating(Long videoId, Double averageRating) {
        videoMapper.updateAverageRating(videoId, averageRating);
    }
}
