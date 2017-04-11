package com.artamonov.placeur.service;


import com.artamonov.placeur.dao.*;

public interface DatabaseService {
    String NAME = "placeur_DatabaseService";

    ICityDAO CITY();
    IPlaceDAO PLACE();
    IUserDAO USER();
    IRatingDAO RATING();
    ITokenDAO TOKEN();
}