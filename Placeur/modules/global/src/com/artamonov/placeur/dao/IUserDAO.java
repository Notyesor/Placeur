package com.artamonov.placeur.dao;

import com.artamonov.placeur.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface IUserDAO {
    public List<UserDTO> findAll();
    public UserDTO findById(UUID id);
    public UserDTO findByNickname(String nickname);
    public boolean update(UserDTO userDTO);
    public boolean create(String nickname, String mail, String name, String surname, UUID city, String password);
    public boolean delete(UserDTO userDTO);
}
