package com.artamonov.placeur.service;

public interface RatingService {
    String NAME = "placeur_RatingService";
    String makeRecommendation(String id);
    String getTopPlaces();
}