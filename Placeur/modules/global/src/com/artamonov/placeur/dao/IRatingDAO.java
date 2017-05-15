package com.artamonov.placeur.dao;

import com.artamonov.placeur.dto.RatingDTO;

import java.util.List;
import java.util.UUID;

public interface IRatingDAO {
    List<RatingDTO> findAll();
    List<RatingDTO> findAllWithoutUserId(UUID userId);
    RatingDTO findById(UUID id);
    List<RatingDTO> findByPlace(UUID id);
    RatingDTO findByPlaceAndUser(UUID userId, UUID placeId);
    boolean update(RatingDTO ratingDTO);
    boolean create(UUID user, UUID place, Double mark, String description);
    boolean delete(RatingDTO ratingDTO);
}
