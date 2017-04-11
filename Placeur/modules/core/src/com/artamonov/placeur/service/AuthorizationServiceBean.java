package com.artamonov.placeur.service;

import com.artamonov.placeur.dto.TokenDTO;
import com.artamonov.placeur.dto.UserDTO;
import com.artamonov.placeur.service.auth.Tokenizer;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(AuthorizationService.NAME)
public class AuthorizationServiceBean implements AuthorizationService {

    @Inject
    private DatabaseService databaseService;

    @Override
    public String signin(String nickname, String password) {
        if (onLoginSuccess(nickname, password)) {
            String token = Tokenizer.createToken(nickname, password);

            UserDTO userDTO = databaseService.USER().findByNickname(nickname);
            if (userDTO != null) {
                TokenDTO tokenDTO = databaseService.TOKEN().findByUserId(userDTO.getId());
                if (tokenDTO != null) {
                    tokenDTO.setValue(token);
                    boolean status = databaseService.TOKEN().update(tokenDTO);
                    if (status) {
                        return "{token: '" + token + "'}";
                    } else {
                        return "{token: 'fail";
                    }
                } else {
                    boolean status = databaseService.TOKEN().create(userDTO.getId(), token);
                    if (status) {
                        return "{token: '" + token + "'}";
                    } else {
                        return "{token: 'fail";
                    }
                }
            }
        } else return "{token: 'invalidData'}";
        return "{token: 'fail'}";
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