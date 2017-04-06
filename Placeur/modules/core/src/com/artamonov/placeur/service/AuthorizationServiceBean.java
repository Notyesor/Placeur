package com.artamonov.placeur.service;

import com.artamonov.placeur.entity.Token;
import com.artamonov.placeur.entity.User;
import com.artamonov.placeur.service.auth.Tokenizer;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.NoResultException;

@Service(AuthorizationService.NAME)
public class AuthorizationServiceBean implements AuthorizationService {

    @Inject
    Metadata metadata;
    @Inject
    Persistence persistence;

    @Override
    public String login(String login, String password) {
        if (onLoginSuccess(login, password)) {
            String token = Tokenizer.createToken(login, password);
            try (Transaction transaction = persistence.createTransaction()) {
                EntityManager em = persistence.getEntityManager();
                User user = (User) em.createQuery("SELECT u FROM placeur$User u WHERE u.nickname = :login")
                        .setParameter("login", login).getSingleResult();
                try {
                    Token oldToken = (Token) em.createQuery("SELECT t FROM placeur$Token t WHERE t.user.id = :userId")
                            .setParameter("userId", user.getId()).getSingleResult();
                    oldToken.setToken(token);
                } catch (NullPointerException | NoResultException e) {
                    Token newToken = metadata.create(Token.class);
                    newToken.setUser(user);
                    newToken.setToken(token);
                    em.persist(newToken);
                }
                transaction.commit();
                return "{token: '" + token + "'}";
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        } else return "{token: 'invalidData'}";
        return "{token: 'fail'}";
    }

    private boolean onLoginSuccess(String login, String password) {
        try(Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            try {
                User user = (User) em.createQuery("SELECT u FROM placeur$User u WHERE u.nickname = :login AND u.password = :password")
                        .setParameter("login", login).setParameter("password", password).getSingleResult();
                return true;
            } catch (NoResultException e) {
                return false;
            }
        }
    }

    @Override
    public String register(String serializedRegisterInfo) {
        return null;
    }
}