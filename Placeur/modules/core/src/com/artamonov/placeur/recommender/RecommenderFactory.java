package com.artamonov.placeur.recommender;

import com.artamonov.placeur.service.DatabaseService;

public class RecommenderFactory {

    public static Recommender createRecommender(Recommenders recommender, DatabaseService databaseService) {
        switch (recommender) {
            case COS: return new CosRecommender(databaseService);
            case EVCLID: return new EvclidRecommender(databaseService);
            case PIRSON: return new PirsonRecommender(databaseService);
            case MANHETTEN: return new ManhettenRecommender(databaseService);
        }
        throw new IllegalArgumentException();
    }
}
