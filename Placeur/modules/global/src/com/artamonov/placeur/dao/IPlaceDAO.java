package com.artamonov.placeur.dao;

import com.artamonov.placeur.dto.PlaceDTO;

import java.util.List;
import java.util.UUID;

public interface IPlaceDAO {
    public List<PlaceDTO> findAll();
    public PlaceDTO findById(UUID id);
    public boolean update(PlaceDTO placeDTO);
    public boolean create(String title, String description, String address, UUID cityId,
                          UUID pictureId, Double latitude, Double longitude);
    public boolean delete(PlaceDTO placeDTO);
}
