package com.artamonov.placeur.dao;

import com.artamonov.placeur.dto.UserDTO;
import com.artamonov.placeur.entity.City;
import com.artamonov.placeur.entity.User;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.Metadata;
import jdk.nashorn.internal.runtime.ECMAException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAO implements IUserDAO {

    private Persistence persistence;
    private Metadata metadata;

    public UserDAO(Persistence persistence, Metadata metadata) {
        this.persistence = persistence;
        this.metadata = metadata;
    }

    @Override
    public List<UserDTO> findAll() {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            List<User> userList = em.createQuery("SELECT u FROM placeur$User u").getResultList();
            List<UserDTO> userDTOList = new ArrayList<>();
            for (User user : userList) {
                userDTOList.add(new UserDTO(user.getId(), user.getNickname(), user.getMail(),
                        user.getName(), user.getSurname(), user.getCity().getId(), user.getPassword()));
            }
            return userDTOList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserDTO findById(UUID id) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            User user = (User) em.createQuery("SELECT u FROM placeur$User u WHERE u.id = :userId")
                    .setParameter("userId", id)
                    .getSingleResult();
            return new UserDTO(user.getId(), user.getNickname(), user.getMail(),
                    user.getName(), user.getSurname(), user.getCity().getId(), user.getPassword());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserDTO findByNickname(String nickname) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            User user = (User) em.createQuery("SELECT u FROM placeur$User u WHERE u.nickname = :nickname")
                    .setParameter("nickname", nickname)
                    .getSingleResult();
            return new UserDTO(user.getId(), user.getNickname(), user.getMail(),
                    user.getName(), user.getSurname(), user.getCity().getId(), user.getPassword());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(UserDTO userDTO) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            User user = (User) em.createQuery("SELECT u FROM placeur$User u WHERE u.id = :userId")
                    .setParameter("userId", userDTO.getId())
                    .getSingleResult();
            City city = (City) em.createQuery("SELECT c FROM placeur$City c WHERE c.id = :cityId")
                    .setParameter("cityId", userDTO.getCity())
                    .getSingleResult();
            user.setNickname(userDTO.getNickname());
            user.setMail(userDTO.getMail());
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setPassword(userDTO.getPassword());
            user.setCity(city);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean create(String nickname, String mail, String name, String surname, UUID cityId, String password) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            User user = metadata.create(User.class);
            City city = (City) em.createQuery("SELECT c FROM placeur$City c WHERE c.id = :cityId")
                    .setParameter("cityId", cityId)
                    .getSingleResult();
            user.setNickname(nickname);
            user.setMail(mail);
            user.setName(name);
            user.setSurname(surname);
            user.setPassword(password);
            user.setCity(city);
            em.persist(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(UserDTO userDTO) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            User user = (User) em.createQuery("SELECT u FROM placeur$User u WHERE u.id = :userId")
                    .setParameter("userId", userDTO.getId())
                    .getSingleResult();
            em.remove(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
