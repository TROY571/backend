package com.example.backend.mapper;

import com.example.backend.model.Favorite;
import com.example.backend.model.FavoriteDto;
import com.example.backend.model.ForumReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    Favorite findByFavorId(Long favoriteId);


    List<FavoriteDto> findByUserId(Long userId);

    void insertFavorite(Favorite favorite);

    void deleteFavorite(Long favoriteId);

    Favorite findByVideoIdAndUserId(@Param("videoId") Long videoId,@Param("userId")  Long userId);

    void deleteByUserId(Long userId);

    void deleteByVideoId(Long videoId);
}
