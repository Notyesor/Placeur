package com.artamonov.placeur.dao;

import com.artamonov.placeur.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface IUserDAO {
    List<UserDTO> findAll();
    List<UserDTO> findAllWithoutId(UUID id);
    UserDTO findById(UUID id);
    UserDTO findByNickname(String nickname);
    boolean update(UserDTO userDTO);
    boolean create(String nickname, UUID cityId, String password, Integer similarity);
    boolean delete(UserDTO userDTO);
}
