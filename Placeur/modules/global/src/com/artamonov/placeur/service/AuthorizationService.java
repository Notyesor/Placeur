package com.artamonov.placeur.service;


public interface AuthorizationService {
    String NAME = "placeur_AuthorizationService";
    String signin(String login, String password);
    String register(String serializedRegisterInfo);
}