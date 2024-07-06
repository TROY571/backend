package com.example.backend.service;

import com.example.backend.mapper.RatingMapper;
import com.example.backend.mapper.VideoMapper;
import com.example.backend.model.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RatingServiceTest {

    @InjectMocks
    private RatingService ratingService;

    @Mock
    private RatingMapper ratingMapper;

    @Mock
    private VideoMapper videoMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByRatingId() {
        Rating rating = new Rating();
        rating.setRatingId(1L);
        when(ratingMapper.findByRatingId(1L)).thenReturn(rating);

        Rating result = ratingService.findByRatingId(1L);
        assertEquals(1L, result.getRatingId());
    }

    @Test
    public void testFindByVideoId() {
        List<Rating> ratings = List.of(new Rating(), new Rating());
        when(ratingMapper.findByVideoId(1L)).thenReturn(ratings);

        List<Rating> result = ratingService.findByVideoId(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testInsertRating() {
        Rating rating = new Rating();
        rating.setVideoId(1L);
        ratingService.insertRating(rating);
        verify(ratingMapper, times(1)).insertRating(rating);
        verify(videoMapper, times(1)).updateAverageRating(eq(1L), anyDouble());
    }

    @Test
    public void testUpdateRating() {
        Rating rating = new Rating();
        rating.setVideoId(1L);
        ratingService.updateRating(rating);
        verify(ratingMapper, times(1)).updateRating(rating);
        verify(videoMapper, times(1)).updateAverageRating(eq(1L), anyDouble());
    }

    @Test
    public void testDeleteRating() {
        Rating rating = new Rating();
        rating.setVideoId(1L);
        when(ratingMapper.findByRatingId(1L)).thenReturn(rating);

        ratingService.deleteRating(1L);
        verify(ratingMapper, times(1)).deleteRating(1L);
        verify(videoMapper, times(1)).updateAverageRating(eq(1L), anyDouble());
    }

    @Test
    public void testFindAverageRatingByVideoId() {
        when(ratingMapper.findAverageRatingByVideoId(1L)).thenReturn(4.5);

        Double averageRating = ratingService.findAverageRatingByVideoId(1L);
        assertEquals(4.5, averageRating);
        verify(videoMapper, times(1)).updateAverageRating(1L, 4.5);
    }
}
