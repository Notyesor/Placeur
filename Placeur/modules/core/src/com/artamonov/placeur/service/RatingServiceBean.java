package com.artamonov.placeur.service;

import com.artamonov.placeur.entity.Place;
import com.artamonov.placeur.entity.Rating;
import com.artamonov.placeur.recommender.RecommenderFactory;
import com.artamonov.placeur.recommender.Recommenders;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;

@Service(RatingService.NAME)
public class RatingServiceBean implements RatingService {

    @Inject
    private Persistence persistence;

    @Override
    public String makeRecommendation(String id) {
        UUID uuid = UUID.fromString(id);
        String cosResult = RecommenderFactory.createRecommender(Recommenders.COS).calculateRatings(uuid, persistence);
        String evclidResult = RecommenderFactory.createRecommender(Recommenders.EVCLID).calculateRatings(uuid, persistence);
        String pirsonResult = RecommenderFactory.createRecommender(Recommenders.PIRSON).calculateRatings(uuid, persistence);
        String manhettenResult = RecommenderFactory.createRecommender(Recommenders.MANHETTEN).calculateRatings(uuid, persistence);
        return cosResult + evclidResult + pirsonResult + manhettenResult;
    }

    @Override
    public String getTopPlaces() {
        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager manager = persistence.getEntityManager();
            List<Place> placeList = manager.createQuery("SELECT p FROM placeur$Place p").getResultList();
            List<String> list = new ArrayList<>();
            for (Place place : placeList) {
                double mark = getAverageMark(place);
                list.add(place.getTitle() + ": " + mark);
            }
            list.sort(Comparator.comparing(o -> new Double(((String) o).split(": ")[1])).reversed());
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : list) {
                stringBuilder.append(s).append("\n\r");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    private double getAverageMark(Place place) {
        try(Transaction transaction = persistence.createTransaction()) {
            EntityManager manager = persistence.getEntityManager();
            List<Rating> ratingList = manager.createQuery("SELECT r FROM placeur$Rating r WHERE r.place.id = :placeId")
                    .setParameter("placeId", place.getId()).getResultList();
            double result = 0;
            for (Rating rating : ratingList) {
                result += rating.getMark();
            }
            result = result / ratingList.size();
            return result;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return -1;
        }
    }
}