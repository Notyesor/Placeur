package com.artamonov.placeur.service;

import com.artamonov.placeur.dto.*;
import com.artamonov.placeur.recommender.RecommenderFactory;
import com.artamonov.placeur.recommender.Recommenders;
import com.google.gson.Gson;
import com.haulmont.cuba.core.Persistence;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;

@Service(RatingService.NAME)
public class RatingServiceBean implements RatingService {

    @Inject
    private DatabaseService databaseService;

    @Override
    public String getRecommendations(String id) {
        UUID uuid = UUID.fromString(id);
        List<MarkedPlaceDTO> list = RecommenderFactory.createRecommender(Recommenders.COS, databaseService)
                .calculateRatings(uuid);
        for (MarkedPlaceDTO place : list) {
            if (Double.isNaN(place.getMark())) place.setMark(0d);
        }
        return new Gson().toJson(new ListToken(list, "ok"));
    }

    @Override
    public String getTopPlaces() {
        List<PlaceDTO> placeDTOList = databaseService.PLACE().findAll();
        List<MarkedPlaceDTO> list = new ArrayList<>();
        for (PlaceDTO place : placeDTOList) {
            double mark = getAverageMark(place);
            list.add(new MarkedPlaceDTO(place, mark));
        }
        list.sort(Comparator.comparing(MarkedPlaceDTO::getMark).reversed());
        return new Gson().toJson(new ListToken(list, "ok"));
    }

    private double getAverageMark(PlaceDTO place) {
        List<RatingDTO> ratingDTOList = databaseService.RATING().findByPlace(place.getId());
        if (ratingDTOList != null) {
            double result = 0;
            for (RatingDTO rating : ratingDTOList) {
                result += rating.getMark();
            }
            result = result / ratingDTOList.size();
            return result;
        } else return -1;
    }
}