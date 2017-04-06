/*
 * TODO Copyright
 */

package com.artamonov.placeur.recommender;

import java.util.List;

class EvclidRecommender extends Recommender {
    @Override
    double calculateSimilarity(List<Double> user1, List<Double> user2) {
        double result = 0;
        for (int i = 0; i < user1.size(); i++) {
            result += (user1.get(i) - user2.get(i)) * (user1.get(i) - user2.get(i));
        }
        return Math.sqrt(result);
    }
}
