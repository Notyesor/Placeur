/*
 * TODO Copyright
 */

package com.artamonov.placeur.recommender;

import com.artamonov.placeur.service.DatabaseService;

import java.util.List;

class PirsonRecommender extends Recommender {
    protected PirsonRecommender(DatabaseService databaseService) {
        super(databaseService);
    }

    @Override
    double calculateSimilarity(List<Double> user1, List<Double> user2) {
        double middle1 = 0;
        double middle2 = 0;
        for (int i = 0; i < user1.size(); i++) {
            middle1 += user1.get(i);
            middle2 += user2.get(i);
        }
        middle1 = middle1 / user1.size();
        middle2 = middle2 / user2.size();
        double numerator = 0;
        double denominator1 = 0;
        double denominator2 = 0;
        for (int i = 0; i < user1.size(); i++) {
            numerator += (user1.get(i) - middle1) * (user2.get(i) - middle2);
            denominator1 += (user1.get(i) - middle1) * (user1.get(i) - middle1);
            denominator2 += (user2.get(i) - middle2) * (user2.get(i) - middle2);
        }
        double result = numerator / Math.sqrt(denominator1 * denominator2);
        return result;
    }
}
