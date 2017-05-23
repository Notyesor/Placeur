package com.artamonov.placeur.service;

import com.artamonov.placeur.dto.UserDTO;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.UUID;

@Service(AuthorizationService.NAME)
public class AuthorizationServiceBean implements AuthorizationService {

    @Inject
    private DatabaseService databaseService;

    @Override
    public String signin(String nickname, String password) {
        if (onLoginSuccess(nickname, password)) {
            UserDTO userDTO = databaseService.USER().findByNickname(nickname);
            if (userDTO != null) {
                return new Gson().toJson(userDTO);
            }
        }
        return new Gson().toJson(null);
    }

    private boolean onLoginSuccess(String nickname, String password) {
        UserDTO userDTO = databaseService.USER().findByNickname(nickname);
        return userDTO != null && userDTO.getPassword().equals(password);
    }

    @Override
    public String signup(String nickname, String password, UUID cityId) {
        if (databaseService.USER().findByNickname(nickname) == null) {
            boolean status = databaseService.USER().create(nickname, cityId, password, 0);
            if (status) {
                UserDTO userDTO = databaseService.USER().findByNickname(nickname);
                return new Gson().toJson(userDTO);
            }
        }
        return new Gson().toJson(null);
    }
}