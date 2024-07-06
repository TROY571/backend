package com.example.backend.controller;

import com.example.backend.model.Rating;
import com.example.backend.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long id) {
        return ResponseEntity.ok(ratingService.findByRatingId(id));
    }

    @GetMapping("/video/{videoId}")
    public ResponseEntity<List<Rating>> getRatingsByVideoId(@PathVariable Long videoId) {
        return ResponseEntity.ok(ratingService.findByVideoId(videoId));
    }

    @PostMapping("/")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        rating.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        ratingService.insertRating(rating);
        return ResponseEntity.ok(rating);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable Long id, @RequestBody Rating rating) {
        rating.setRatingId(id);
        ratingService.updateRating(rating);
        return ResponseEntity.ok(rating);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/video/{videoId}/average")
    public ResponseEntity<Double> getAverageRatingByVideoId(@PathVariable Long videoId) {
        Double averageRating = ratingService.findAverageRatingByVideoId(videoId);
        return ResponseEntity.ok(averageRating);
    }
}
