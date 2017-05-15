package com.artamonov.placeur.dao;

import com.artamonov.placeur.dto.CityDTO;

import java.util.List;
import java.util.UUID;

public interface ICityDAO {
    List<CityDTO> findAll();
    CityDTO findById(UUID id);
    boolean update(CityDTO cityDTO);
    boolean create(String title, Double latitude, Double longitude);
    boolean delete(CityDTO cityDTO);
}
