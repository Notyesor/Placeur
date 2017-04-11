package com.artamonov.placeur.dao;

import com.artamonov.placeur.dto.PlaceDTO;
import com.artamonov.placeur.entity.City;
import com.artamonov.placeur.entity.Place;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.Metadata;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlaceDAO implements IPlaceDAO {

    Persistence persistence;
    Metadata metadata;

    public PlaceDAO(Persistence persistence, Metadata metadata) {
        this.persistence = persistence;
        this.metadata = metadata;
    }

    @Override
    public List<PlaceDTO> findAll() {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            List<Place> placeList = em.createQuery("SELECT p FROM placeur$Place p").getResultList();
            List<PlaceDTO> placeDTOList = new ArrayList<>();
            for (Place place : placeList) {
                placeDTOList.add(new PlaceDTO(place.getId(), place.getTitle(), place.getDescription(),
                        place.getAddress(), place.getCity().getId(), place.getLatitude(), place.getLongitude()));
            }
            return placeDTOList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PlaceDTO findById(UUID id) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            Place place = (Place) em.createQuery("SELECT p FROM placeur$Place p WHERE p.id = :placeId")
                    .setParameter("placeId", id)
                    .getSingleResult();
            return new PlaceDTO(place.getId(), place.getTitle(), place.getDescription(),
                    place.getAddress(), place.getCity().getId(), place.getLatitude(), place.getLongitude());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(PlaceDTO placeDTO) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            Place place = (Place) em.createQuery("SELECT p FROM placeur$Place p WHERE p.id = :placeId")
                    .setParameter("placeId", placeDTO.getId())
                    .getSingleResult();
            City city = (City) em.createQuery("SELECT c FROM placeur$City c WHERE c.id = :cityId")
                    .setParameter("cityId", placeDTO.getCityId())
                    .getSingleResult();
            place.setTitle(placeDTO.getTitle());
            place.setDescription(placeDTO.getDescription());
            place.setAddress(placeDTO.getAddress());
            place.setCity(city);
            place.setLatitude(placeDTO.getLatitude());
            place.setLongitude(placeDTO.getLongitude());
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean create(String title, String description, String address,
                          UUID cityId, UUID pictureId, Double latitude, Double longitude) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            Place place = metadata.create(Place.class);
            City city = (City) em.createQuery("SELECT c FROM placeur$City c WHERE c.id = :cityId")
                    .setParameter("cityId", cityId)
                    .getSingleResult();
            place.setTitle(title);
            place.setDescription(description);
            place.setAddress(address);
            place.setCity(city);
            place.setLatitude(latitude);
            place.setLongitude(longitude);
            em.persist(place);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(PlaceDTO placeDTO) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            Place place = (Place) em.createQuery("SELECT p FROM placeur$Place p WHERE p.id = :placeId")
                    .setParameter("placeId", placeDTO.getId())
                    .getSingleResult();
            em.remove(place);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
