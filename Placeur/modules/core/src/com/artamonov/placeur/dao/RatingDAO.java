package com.artamonov.placeur.dao;

import com.artamonov.placeur.dto.RatingDTO;
import com.artamonov.placeur.entity.Place;
import com.artamonov.placeur.entity.Rating;
import com.artamonov.placeur.entity.User;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.Metadata;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RatingDAO implements IRatingDAO {

    private Persistence persistence;
    private Metadata metadata;

    public RatingDAO(Persistence persistence, Metadata metadata) {
        this.persistence = persistence;
        this.metadata = metadata;
    }

    @Override
    public List<RatingDTO> findAll() {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            List<Rating> ratingList = em.createQuery("SELECT r FROM placeur$Rating r").getResultList();
            List<RatingDTO> ratingDTOList = new ArrayList<>();
            for (Rating rating : ratingList) {
                ratingDTOList.add(new RatingDTO(rating.getId(), rating.getUser().getId(),
                        rating.getPlace().getId(), rating.getMark(), rating.getDescription()));
            }
            return ratingDTOList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public RatingDTO findById(UUID id) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            Rating rating = (Rating) em.createQuery("SELECT r FROM placeur$Rating r WHERE r.id = :ratingId")
                    .setParameter("ratingId", id)
                    .getSingleResult();
            return new RatingDTO(rating.getId(), rating.getUser().getId(),
                    rating.getPlace().getId(), rating.getMark(), rating.getDescription());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(RatingDTO ratingDTO) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            Rating rating = (Rating) em.createQuery("SELECT r FROM placeur$Rating r WHERE r.id = :ratingId")
                    .setParameter("ratingId", ratingDTO.getId())
                    .getSingleResult();
            User user = (User) em.createQuery("SELECT u FROM placeur$User u WHERE u.id = :userId")
                    .setParameter("userId", ratingDTO.getUser())
                    .getSingleResult();
            Place place = (Place) em.createQuery("SELECT p FROM placeur$User p WHERE p.id = :placeId")
                    .setParameter("placeId", ratingDTO.getPlace())
                    .getSingleResult();
            rating.setUser(user);
            rating.setPlace(place);
            rating.setDescription(ratingDTO.getDescription());
            rating.setMark(ratingDTO.getMark());
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean create(UUID userId, UUID placeId, Double mark, String description) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            Rating rating = metadata.create(Rating.class);
            User user = (User) em.createQuery("SELECT u FROM placeur$User u WHERE u.id = :userId")
                    .setParameter("userId", userId)
                    .getSingleResult();
            Place place = (Place) em.createQuery("SELECT p FROM placeur$User p WHERE p.id = :placeId")
                    .setParameter("placeId", placeId)
                    .getSingleResult();
            rating.setUser(user);
            rating.setPlace(place);
            rating.setDescription(description);
            rating.setMark(mark);
            em.persist(rating);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(RatingDTO ratingDTO) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            Rating rating = (Rating) em.createQuery("SELECT r FROM placeur$Rating r WHERE r.id = :ratingId")
                    .setParameter("ratingId", ratingDTO.getId())
                    .getSingleResult();
            em.remove(rating);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
