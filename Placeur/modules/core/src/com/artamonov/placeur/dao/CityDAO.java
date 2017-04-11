package com.artamonov.placeur.dao;

import com.artamonov.placeur.dto.CityDTO;
import com.artamonov.placeur.entity.City;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.Metadata;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CityDAO implements ICityDAO {
    private Persistence persistence;
    private Metadata metadata;

    public CityDAO(Persistence persistence, Metadata metadata) {
        this.persistence = persistence;
        this.metadata = metadata;
    }

    @Override
    public List<CityDTO> findAll() {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            List<City> cityList = em.createQuery("SELECT c FROM placeur$City c").getResultList();
            List<CityDTO> cityDTOList = new ArrayList<>();
            for (City city : cityList) {
                cityDTOList.add(new CityDTO(city.getId(), city.getTitle(), city.getLatitude(), city.getLongitude()));
            }
            return cityDTOList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CityDTO findById(UUID id) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            City city = (City) em.createQuery("SELECT c FROM placeur$City c WHERE c.id = :cityId")
                    .setParameter("cityId", id)
                    .getSingleResult();
            return new CityDTO(city.getId(), city.getTitle(), city.getLatitude(), city.getLongitude());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(CityDTO cityDTO) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            City city = (City) em.createQuery("SELECT c FROM placeur$City c WHERE c.id = :cityId")
                    .setParameter("cityId", cityDTO.getId())
                    .getSingleResult();
            city.setTitle(cityDTO.getTitle());
            city.setLatitude(cityDTO.getLatitude());
            city.setLongitude(cityDTO.getLongitude());
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean create(String title, Double latitude, Double longitude) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            City newCity = metadata.create(City.class);
            newCity.setTitle(title);
            newCity.setLatitude(latitude);
            newCity.setLongitude(longitude);
            em.persist(newCity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(CityDTO cityDTO) {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();

            City city = (City) em.createQuery("SELECT c FROM placeur$City c WHERE c.id = :cityId")
                    .setParameter("cityId", cityDTO.getId())
                    .getSingleResult();
            em.remove(city);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
