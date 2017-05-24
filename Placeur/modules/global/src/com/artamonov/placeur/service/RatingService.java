package com.artamonov.placeur.service;

public interface RatingService {
    String NAME = "placeur_RatingService";
    String getRecommendations(String id);
    String getRatings(String id);
    String getTopPlaces();
}