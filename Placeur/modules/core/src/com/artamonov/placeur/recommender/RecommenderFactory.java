/*
 * TODO Copyright
 */

package com.artamonov.placeur.recommender;

public class RecommenderFactory {

    public static Recommender createRecommender(Recommenders recommender) {
        switch (recommender) {
            case COS: return new CosRecommender();
            case EVCLID: return new EvclidRecommender();
            case PIRSON: return new PirsonRecommender();
            case MANHETTEN: return new ManhettenRecommender();
        }
        throw new IllegalArgumentException();
    }
}
