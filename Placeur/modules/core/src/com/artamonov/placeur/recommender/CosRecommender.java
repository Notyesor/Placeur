/*
 * TODO Copyright
 */

package com.artamonov.placeur.recommender;

import java.util.List;

public class CosRecommender extends Recommender {
    @Override
    double calculateSimilarity(List<Double> user1, List<Double> user2) {
        double a = 0, b = 0, c = 0;
        for (int i = 0; i < user1.size(); i++) {
            double v1 = user1.get(i);
            double v2 = user2.get(i);
            a += v1 * v2;
            b += v1 * v1;
            c += v2 * v2;
        }
        return a / (Math.sqrt(b) * Math.sqrt(c));
    }
}
