package com.artamonov.placeur.dao;

import com.artamonov.placeur.dto.CityDTO;

import java.util.List;
import java.util.UUID;

public interface ICityDAO {
    public List<CityDTO> findAll();
    public CityDTO findById(UUID id);
    public boolean update(CityDTO cityDTO);
    public boolean create(String title, Double latitude, Double longitude);
    public boolean delete(CityDTO cityDTO);
}
