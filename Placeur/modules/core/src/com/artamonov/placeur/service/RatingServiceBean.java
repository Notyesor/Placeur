package com.artamonov.placeur.service;

import com.artamonov.placeur.dto.MarkedPlaceDTO;
import com.artamonov.placeur.dto.PlaceDTO;
import com.artamonov.placeur.dto.RatingDTO;
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
    private Persistence persistence;
    @Inject
    private DatabaseService databaseService;

    @Override
    public String makeRecommendation(String id) {
        UUID uuid = UUID.fromString(id);
        List<MarkedPlaceDTO> result = RecommenderFactory.createRecommender(Recommenders.COS, databaseService)
                .calculateRatings(uuid);
        return new Gson().toJson(result);
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
        return new Gson().toJson(list);
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