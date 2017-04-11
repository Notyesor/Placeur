package com.artamonov.placeur.dao;

import com.artamonov.placeur.dto.TokenDTO;
import com.artamonov.placeur.entity.Token;
import com.artamonov.placeur.entity.User;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.Metadata;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TokenDAO implements ITokenDAO {

    private Persistence persistence;
    private Metadata metadata;

    public TokenDAO(Persistence persistence, Metadata metadata) {
        this.persistence = persistence;
        this.metadata = metadata;
    }

    @Override
    public List<TokenDTO> findAll() {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            List<Token> tokenList = em.createQuery("SELECT t FROM placeur$Token t").getResultList();
            List<TokenDTO> tokenDTOList = new ArrayList<>();
            for (Token token : tokenList) {
                tokenDTOList.add(new TokenDTO(token.getId(), token.getUser().getId(), token.getToken()));
            }
            return tokenDTOList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TokenDTO findById(UUID id) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            Token token = (Token) em.createQuery("SELECT t FROM placeur$Token t WHERE t.id = :tokenId")
                    .setParameter("tokenId", id)
                    .getSingleResult();
            return new TokenDTO(token.getId(), token.getUser().getId(), token.getToken());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(TokenDTO tokenDTO) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            Token token = (Token) em.createQuery("SELECT t FROM placeur$Token t WHERE t.id = :tokenId")
                    .setParameter("tokenId", tokenDTO.getId())
                    .getSingleResult();
            User user = (User) em.createQuery("SELECT u FROM placeur$User u WHERE u.id = :userId")
                    .setParameter("userId", tokenDTO.getUser())
                    .getSingleResult();
            token.setUser(user);
            token.setToken(tokenDTO.getValue());
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean create(UUID userId, String value) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            Token token = metadata.create(Token.class);
            User user = (User) em.createQuery("SELECT u FROM placeur$User u WHERE u.id = :userId")
                    .setParameter("userId", userId)
                    .getSingleResult();
            token.setUser(user);
            token.setToken(value);
            em.persist(token);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(TokenDTO tokenDTO) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            Token token = (Token) em.createQuery("SELECT t FROM placeur$Token t WHERE t.id = :tokenId")
                    .setParameter("tokenId", tokenDTO.getId())
                    .getSingleResult();
            em.remove(token);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
