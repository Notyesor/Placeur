package com.artamonov.placeur.service;


import java.util.UUID;

public interface AuthorizationService {
    String NAME = "placeur_AuthorizationService";
    String signin(String login, String password);
    String signup(String nickname, String password, UUID cityId);
}