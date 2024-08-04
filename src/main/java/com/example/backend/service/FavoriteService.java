package com.example.backend.service;

import com.example.backend.mapper.FavoriteMapper;
import com.example.backend.model.Favorite;
import com.example.backend.model.FavoriteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    public Favorite findByFavorId(Long favoriteId) {
        return favoriteMapper.findByFavorId(favoriteId);
    }

    public List<FavoriteDto> getFavoritesByUserId(Long userId) {
        return favoriteMapper.findByUserId(userId);
    }

    public void insertFavorite(Favorite favorite) {
        favoriteMapper.insertFavorite(favorite);
    }

    public Favorite findByVideoIdAndUserId(Long videoId, Long userId) {
        return favoriteMapper.findByVideoIdAndUserId(videoId,userId);
    }

    public void deleteFavorite(Long favoriteId) {
        favoriteMapper.deleteFavorite(favoriteId);
    }

    public void deleteByUserId(Long userId) {
        favoriteMapper.deleteByUserId(userId);
    }
}