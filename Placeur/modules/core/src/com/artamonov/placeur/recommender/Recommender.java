/*
 * TODO Copyright
 */

package com.artamonov.placeur.recommender;

import com.artamonov.placeur.entity.Place;
import com.artamonov.placeur.entity.Rating;
import com.artamonov.placeur.entity.User;
import com.artamonov.placeur.recommender.wrapper.PlaceWrapper;
import com.artamonov.placeur.recommender.wrapper.RatingWrapper;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;

import java.util.*;

public abstract class Recommender {

    private static final int MAX_USER_COUNT = 100;

    public String calculateRatings(UUID userId, Persistence persistence) {
        try(Transaction transaction = persistence.createTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();
            User user = (User) entityManager.createQuery("SELECT u FROM placeur$User u WHERE u.id = :id")
                    .setParameter("id", userId)
                    .getFirstResult();
            List<User> userList = entityManager.createQuery("SELECT u FROM placeur$User u WHERE NOT u.id = :id")
                    .setParameter("id", userId)
                    .getResultList();

            HashMap<User, Double> similarity = new HashMap<>();
            List<Place> places = null;

            for (User currentUser : userList) {
                places = entityManager.createQuery("SELECT p FROM placeur$Place p").getResultList();
                List<Double> firstVectorList = new ArrayList<>();
                List<Double> secondVectorList = new ArrayList<>();
                for (int i = 0; i < places.size(); i++) {
                    List<Double> firstVectorQueryResult = entityManager.createQuery(
                            "SELECT r.mark FROM placeur$Rating r WHERE r.place.id = :placeId AND r.user.id = :userId")
                            .setParameter("placeId", places.get(i).getId())
                            .setParameter("userId", userId)
                            .getResultList();

                    List<Double> secondVectorQueryResult = entityManager.createQuery(
                            "SELECT r.mark FROM placeur$Rating r WHERE r.place.id = :placeId AND r.user.id = :userId")
                            .setParameter("placeId", places.get(i).getId())
                            .setParameter("userId", currentUser)
                            .getResultList();

                    if (firstVectorQueryResult.isEmpty()) {
                        firstVectorList.add(i, 0d);
                    } else {
                        firstVectorList.add(i, firstVectorQueryResult.get(0));
                    }
                    if (secondVectorQueryResult.isEmpty()) {
                        secondVectorList.add(i, 0d);
                    } else {
                        secondVectorList.add(i, secondVectorQueryResult.get(0));
                    }
                }
                similarity.put(currentUser, calculateSimilarity(firstVectorList, secondVectorList));
            }

            List<Rating> ratingList = entityManager.createQuery("SELECT r FROM placeur$Rating r WHERE NOT r.user.id = :userId")
                    .setParameter("userId", userId)
                    .getResultList();
            List<RatingWrapper> ratingWrapperList = new ArrayList<>();
            for (Rating rating : ratingList) {
                ratingWrapperList.add(new RatingWrapper(rating, similarity.get(rating.getUser())));
            }
            System.out.println(ratingWrapperList);
            List<PlaceWrapper> placeWrapperList = new ArrayList<>();
            for (Place place : places) {
                double sum = getSum(place, ratingWrapperList);
                List<Rating> userRatingMark = entityManager.createQuery(
                        "SELECT r FROM placeur$Rating r WHERE r.user.id = :userId AND r.place.id = :placeId AND r.isRecommended = FALSE")
                        .setParameter("userId", userId)
                        .setParameter("placeId", place.getId())
                        .getResultList();
                if (userRatingMark.isEmpty()) {
                    placeWrapperList.add(new PlaceWrapper(place, sum));
                }
            }
            System.out.println(placeWrapperList);
            double similaritySum = getSimilaritySum(similarity, MAX_USER_COUNT);
            for (PlaceWrapper placeWrapper : placeWrapperList) {
                placeWrapper.setMark(placeWrapper.getMark() / similaritySum);
            }
            placeWrapperList.sort((o1, o2) -> {
                if (o2.getMark() > o1.getMark()) return 1;
                if (o2.getMark() < o1.getMark()) return -1;
                else return 0;
            });
            StringBuilder builder = new StringBuilder("Recommendation list for user ")
                    .append(user.getName())
                    .append(" (id: ")
                    .append(user.getId())
                    .append("):\n");
            for (int i = 0; i < placeWrapperList.size(); i++) {
                builder.append(placeWrapperList.get(i)).append("\n");
            }
            return builder.toString();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    private double getSum(Place place, List<RatingWrapper> ratingWrapperList) {
        double sum = 0;
        for (RatingWrapper ratingWrapper : ratingWrapperList) {
            if (ratingWrapper.getPlace().equals(place)) sum += ratingWrapper.getMark();
        }
        return sum;
    }

    private double getSimilaritySum(HashMap<User, Double> similarity, int maxUserCount) {
        double sum = 0;
        List<Double> list = new ArrayList<>(similarity.values());
        Collections.sort(list, Collections.reverseOrder());
        for (int i = 0; i < list.size(); i++) {
            if (i == maxUserCount) return sum;
            sum += list.get(i);
        }
        return sum;
    }


    abstract double calculateSimilarity(List<Double> user1, List<Double> user2);
}
