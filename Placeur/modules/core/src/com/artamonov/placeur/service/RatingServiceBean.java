package com.artamonov.placeur.service;

import com.artamonov.placeur.recommender.CosRecommender;
import com.artamonov.placeur.recommender.Recommender;
import com.haulmont.cuba.core.Persistence;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.UUID;

@Service(RatingService.NAME)
public class RatingServiceBean implements RatingService {

    @Inject
    Persistence persistence;

    @Override
    public String makeRecommendation(String id) {
        UUID uuid = UUID.fromString(id);
        Recommender recommender = new CosRecommender();
        return recommender.calculateRatings(uuid, persistence);
    }
}