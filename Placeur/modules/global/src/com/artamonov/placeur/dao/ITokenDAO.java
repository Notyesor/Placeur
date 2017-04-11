package com.artamonov.placeur.dao;

import com.artamonov.placeur.dto.TokenDTO;

import java.util.List;
import java.util.UUID;

public interface ITokenDAO {
    public List<TokenDTO> findAll();
    public TokenDTO findById(UUID id);
    public TokenDTO findByUserId(UUID id);
    public boolean update(TokenDTO tokenDTO);
    public boolean create(UUID user, String value);
    public boolean delete(TokenDTO tokenDTO);
}
