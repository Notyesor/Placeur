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

    private ICityDAO cityDAO = new CityDAO(persistence, metadata);
    private IPlaceDAO placeDAO = new PlaceDAO(persistence, metadata);
    private IUserDAO userDAO = new UserDAO(persistence, metadata);
    private IRatingDAO ratingDAO = new RatingDAO(persistence, metadata);
    private ITokenDAO tokenDAO = new TokenDAO(persistence, metadata);


    @Override
    public ICityDAO CITY() {
        return cityDAO;
    }

    @Override
    public IPlaceDAO PLACE() {
        return placeDAO;
    }

    @Override
    public IUserDAO USER() {
        return userDAO;
    }

    @Override
    public IRatingDAO RATING() {
        return ratingDAO;
    }

    @Override
    public ITokenDAO TOKEN() {
        return tokenDAO;
    }
}