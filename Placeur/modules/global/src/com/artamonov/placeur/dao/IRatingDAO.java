package com.artamonov.placeur.dao;

import com.artamonov.placeur.dto.RatingDTO;

import java.util.List;
import java.util.UUID;

public interface IRatingDAO {
    public List<RatingDTO> findAll();
    public RatingDTO findById(UUID id);
    public boolean update(RatingDTO ratingDTO);
    public boolean create(UUID user, UUID place, Double mark, String description);
    public boolean delete(RatingDTO ratingDTO);
}
