package com.artamonov.placeur.recommender;

import com.artamonov.placeur.dto.*;
import com.artamonov.placeur.service.DatabaseService;

import java.util.*;

public abstract class Recommender {

    private DatabaseService databaseService;

    protected Recommender(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    private static final int MAX_USER_COUNT = 100;

    public List<MarkedPlaceDTO> calculateRatings(UUID userId) {
        UserDTO user = databaseService.USER().findById(userId);
        List<UserDTO> otherUsers = databaseService.USER().findAllWithoutId(userId);
        HashMap<UUID, Double> similarity = new HashMap<>();
        List<PlaceDTO> places = databaseService.PLACE().findAll();
        for (UserDTO currentUser : otherUsers) {
            List<Double> firstVector = new ArrayList<>();
            List<Double> secondVector = new ArrayList<>();
            for (int i = 0; i < places.size(); i++) {
                RatingDTO rating1 = databaseService.RATING().findByPlaceAndUser(user.getId(), places.get(i).getId());
                RatingDTO rating2 = databaseService.RATING().findByPlaceAndUser(currentUser.getId(), places.get(i).getId());
                firstVector.add(i, rating1 != null ? rating1.getMark() : 0d);
                secondVector.add(i, rating2 != null ? rating2.getMark() : 0d);
            }
            similarity.put(currentUser.getId(), calculateSimilarity(firstVector, secondVector));
        }
        List<RatingDTO> ratings = databaseService.RATING().findAllWithoutUserId(user.getId());
        List<MarkedRatingDTO> markedRatings = new ArrayList<>();
        for (RatingDTO rating : ratings) {
            markedRatings.add(new MarkedRatingDTO(rating, rating.getMark() * similarity.get(rating.getUser())));
        }
        List<MarkedPlaceDTO> markedPlaces = new ArrayList<>();
        double similaritySum = getSimilaritySum(similarity);
        for (PlaceDTO place : places) {
            double sum = getSum(place, markedRatings);
            RatingDTO userRating = databaseService.RATING().findByPlaceAndUser(user.getId(), place.getId());
            if (userRating == null) markedPlaces.add(new MarkedPlaceDTO(place, sum / similaritySum));
        }
        markedPlaces.sort((o1, o2) -> {
            if (o2.getMark() > o1.getMark()) return 1;
            if (o2.getMark() < o1.getMark()) return -1;
            else return 0;
        });
        return markedPlaces;
    }

    private double getSum(PlaceDTO place, List<MarkedRatingDTO> markedRatings) {
        double sum = 0;
        for (MarkedRatingDTO rating : markedRatings) {
            if (rating.getRatingDTO().getPlace().equals(place.getId())) sum += rating.getMark();
        }
        return sum;
    }

    private double getSimilaritySum(HashMap<UUID, Double> similarity) {
        double sum = 0;
        List<Double> list = new ArrayList<>(similarity.values());
        list.sort(Collections.reverseOrder());
        for (int i = 0; i < list.size(); i++) {
            if (i == MAX_USER_COUNT) return sum;
            sum += list.get(i);
        }
        return sum;
    }


    abstract double calculateSimilarity(List<Double> user1, List<Double> user2);
}
