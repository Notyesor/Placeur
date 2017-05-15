package com.artamonov.placeur.service;

import com.artamonov.placeur.dao.*;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(DatabaseService.NAME)
public class DatabaseServiceBean implements DatabaseService {

    @Inject
    private Persistence persistence;
    @Inject
    private Metadata metadata;

    private ICityDAO cityDAO;
    private IPlaceDAO placeDAO;
    private IUserDAO userDAO;
    private IRatingDAO ratingDAO;


    @Override
    public ICityDAO CITY() {
        if (cityDAO == null) {
            cityDAO = new CityDAO(persistence, metadata);
        }
        return cityDAO;
    }

    @Override
    public IPlaceDAO PLACE() {
        if (placeDAO == null) {
            placeDAO = new PlaceDAO(persistence, metadata);
        }
        return placeDAO;
    }

    @Override
    public IUserDAO USER() {
        if (userDAO == null) {
            userDAO = new UserDAO(persistence, metadata);
        }
        return userDAO;
    }

    @Override
    public IRatingDAO RATING() {
        if (ratingDAO == null) {
            ratingDAO = new RatingDAO(persistence, metadata);
        }
        return ratingDAO;
    }
}