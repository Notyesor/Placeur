/*
 * TODO Copyright
 */

package com.artamonov.placeur.recommender;

import com.artamonov.placeur.service.DatabaseService;

import java.util.List;

class ManhettenRecommender extends Recommender {
    protected ManhettenRecommender(DatabaseService databaseService) {
        super(databaseService);
    }

    @Override
    double calculateSimilarity(List<Double> user1, List<Double> user2) {
        double result = 0;
        for (int i = 0; i < user1.size(); i++) {
            result += Math.abs(user1.get(i) - user2.get(i));
        }
        return result;
    }
}
