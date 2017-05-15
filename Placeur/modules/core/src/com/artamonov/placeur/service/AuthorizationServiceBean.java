package com.artamonov.placeur.service;

import com.artamonov.placeur.dto.UserDTO;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

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
    public String register(String serializedRegisterInfo) {
        return null;
    }
}