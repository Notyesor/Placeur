package com.artamonov.placeur.dao;

import com.artamonov.placeur.dto.PlaceDTO;

import java.util.List;
import java.util.UUID;

public interface IPlaceDAO {
    List<PlaceDTO> findAll();
    PlaceDTO findById(UUID id);
    boolean update(PlaceDTO placeDTO);
    boolean create(String title, String description, String address, UUID cityId,
                   UUID pictureId, Double latitude, Double longitude);
    boolean delete(PlaceDTO placeDTO);
}
