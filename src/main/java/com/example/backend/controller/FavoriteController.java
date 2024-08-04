package com.example.backend.controller;

import com.example.backend.model.Favorite;
import com.example.backend.model.FavoriteDto;
import com.example.backend.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/toggle/{videoId}/{userId}")
    public ResponseEntity<Favorite> toggleFavorite(@PathVariable Long videoId,@PathVariable Long userId) {
        Favorite favorite=favoriteService.findByVideoIdAndUserId(videoId,userId);
        if(favorite!=null){
            favoriteService.deleteFavorite(favorite.getFavoriteId());
        }else {
            favorite = new Favorite();
            favorite.setUserId(userId);
            favorite.setVideoId(videoId);
            favorite.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            favoriteService.insertFavorite(favorite);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/favorite/{videoId}/{userId}")
    public ResponseEntity<?> getFavorite(@PathVariable Long videoId,@PathVariable Long userId) {
        Favorite favorite=favoriteService.findByVideoIdAndUserId(videoId,userId);
        if(favorite!=null){
            return ResponseEntity.ok(true);
        }else {
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoriteDto>> getFavoritesByUserId(@PathVariable Long userId) {
        List<FavoriteDto> favorites = favoriteService.getFavoritesByUserId(userId);
        return ResponseEntity.ok(favorites);
    }
}